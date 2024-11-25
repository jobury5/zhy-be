package com.zhy.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.zhy.config.RouterValidator;
import com.zhy.constant.SysConstant;
import com.zhy.cqe.token.UserByAccessTokenQuery;
import com.zhy.exception.ApiException;
import com.zhy.result.AppCode;
import com.zhy.result.SysCode;
import com.zhy.result.ZhyResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.PathMatcher;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jobury
 * @date 2024/9/26 14:15
 */
@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String VALIDATE_TOKEN_URL = "/api/token/get-or-refresh-user-by-access-token";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final int TOKEN_PREFIX_LENGTH = 7;

    private static final String USER_ID_HEADER = "USER-ID";

    private static final String NEW_TOKEN_HEADER = "NEW-ACCESS-TOKEN";

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            log.info("uri======" + exchange.getRequest().getURI().getPath());
            if (RouterValidator.NEED_CHECK_TOKEN.test(exchange.getRequest())) {
                String token = getToken(exchange);
                UserByAccessTokenQuery query = new UserByAccessTokenQuery();
                query.setToken(token);
                //判断该token有没有被refresh过，如果刷新过，则从缓存中获取最新的token
                Boolean hasKey = redisTemplate.opsForHash().hasKey(SysConstant.RedisKey.ACCESS_TOKENS, token);
                if (hasKey) {
                    String newToken = (String) redisTemplate.opsForHash().get(SysConstant.RedisKey.ACCESS_TOKENS, token);
                    query.setToken(newToken);
                }
                WebClient webClient = webClientBuilder.baseUrl("lb://zhy").build();
                return webClient
                        .post()
                        .uri(VALIDATE_TOKEN_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(query)
                        .retrieve()
                        .bodyToMono(ZhyResult.class).map(result -> {
                            if (result != null && result.getCode() == SysCode.SUCCESS.getCode() && result.getData() != null && result.getData() instanceof HashMap<?, ?>) {
                                Map userDTO = (HashMap) result.getData();
                                if (userDTO.get("userId") != null) {
                                    String userId = String.valueOf(userDTO.get("userId"));
                                    exchange.getRequest().mutate().header(USER_ID_HEADER, userId);
                                }
                                //是否刷新token
                                Boolean refresh = (Boolean) userDTO.get("refresh");
                                if (refresh) {
                                    Object newAccessTokenObj = userDTO.get("accessToken");
                                    if (newAccessTokenObj != null && newAccessTokenObj instanceof HashMap<?, ?>) {
                                        Map newAccessTokenMap = (HashMap) newAccessTokenObj;
                                        if (newAccessTokenMap.get("accessToken") != null) {
                                            //获取刷新后的access token
                                            String newToken = (String) newAccessTokenMap.get("accessToken");
                                            //将老的access token作为key，新的access token作为value放入缓存中
                                            redisTemplate.opsForHash().put(SysConstant.RedisKey.ACCESS_TOKENS, token, newToken);
                                            //exchange.getRequest().mutate().header(NEW_TOKEN_HEADER, newToken);
                                        }
                                    }
                                }
                                //判断用户的roles是否可以访问当前的url
                                if (userDTO.get("userRoles") != null) {
                                    //当前用户所拥有的角色
                                    List<String> userRoles = (List) userDTO.get("userRoles");
                                    //缓存中的权限和角色对应关系
                                    Map<Object, Object> permRoles = redisTemplate.opsForHash().entries(SysConstant.RedisKey.KEY_PERM_ROLES);
                                    if (CollUtil.isNotEmpty(userRoles) && CollUtil.isNotEmpty(permRoles)) {
                                        String path = exchange.getRequest().getURI().getPath();
                                        String method = exchange.getRequest().getMethod().toString();
                                        String restfulPath = method + ":" + path;
                                        PathMatcher pathMatcher = new AntPathMatcher();
                                        Set<String> hasPermissionRoles = CollectionUtil.newHashSet();
                                        boolean needCheck = false;
                                        // 缓存权限规则的值：有请求路径访问权限的角色集合
                                        for (Map.Entry<Object, Object> permRole : permRoles.entrySet()) {
                                            String perm = (String) permRole.getKey();
                                            if (pathMatcher.match(perm, restfulPath)) {
                                                needCheck = true;
                                                List<String> roles = Convert.toList(String.class, permRole.getValue());
                                                hasPermissionRoles.addAll(roles);
                                            }
                                        }
                                        //如果需要检查权限，则判断用户至少有一个对应权限的角色
                                        if (needCheck && CollUtil.isNotEmpty(hasPermissionRoles)) {
                                            boolean hasRole = false;
                                            for (String role : userRoles) {
                                                if (hasPermissionRoles.contains(role)) {
                                                    hasRole = true;
                                                    break;
                                                }
                                            }
                                            if (!hasRole) {
                                                throw new ApiException(AppCode.AUTH_URL_PERMISSION_FORBID, StrUtil.format("当前用户没有对该Url({})的访问权限", restfulPath));
                                            }
                                        }
                                    }
                                }
                                return exchange;
                            } else {
                                if (result != null && result.getData() != null && result.getData() instanceof String) {
                                    throw new ApiException(result.getCode(), (String) result.getData());
                                } else {
                                    throw new ApiException(SysCode.ACCESS_TOKEN_NOT_VALID);
                                }
                            }
                        }).flatMap(chain::filter);
            } else {
                return chain.filter(exchange);
            }
        });
    }

    public static class Config {
    }

    private String getToken(ServerWebExchange exchange) {
        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
        if(queryParams.containsKey("token")){
            List<String> tokens = queryParams.get("token");
            if(CollUtil.isNotEmpty(tokens) && tokens.get(0) != null) {
                return tokens.get(0);
            }
        }
        HttpHeaders headers = exchange.getRequest().getHeaders();
        //header contains token or not
        if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
            throw new ApiException(SysCode.ACCESS_TOKEN_REQUIRED);
        }
        // Disable check token in api gateway
        List<String> listHeader = headers.get(HttpHeaders.AUTHORIZATION);
        if (CollectionUtils.isEmpty(listHeader) || StrUtil.isBlank(listHeader.get(0))) {
            throw new ApiException(SysCode.ACCESS_TOKEN_REQUIRED);
        }
        String token = listHeader.get(0);
        if (token.startsWith(TOKEN_PREFIX)) {
            token = token.substring(TOKEN_PREFIX_LENGTH);
        } else {
            throw new ApiException(SysCode.ACCESS_TOKEN_REQUIRED);
        }
        return token;
    }
}
