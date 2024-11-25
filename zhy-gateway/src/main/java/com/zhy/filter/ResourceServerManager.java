package com.zhy.filter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: jobury
 * @Date: 2024/9/26 14:15
 */
//@Component
@AllArgsConstructor
@Slf4j
//public class ResourceServerManager implements ReactiveAuthorizationManager {
public class ResourceServerManager {
//    @Override
//    public Mono<AuthorizationDecision> check(Mono authentication, Object object) {
//        return null;
//    }

//    @Override
//    public Mono<AuthorizationDecision> check(Mono mono, Object authorizationContext) {
//        ServerHttpRequest request = ((AuthorizationContext) authorizationContext).getExchange().getRequest();
//        // 预检请求放行
//        if (request.getMethod() == HttpMethod.OPTIONS) {
//            return Mono.just(new AuthorizationDecision(true));
//        }
//        PathMatcher pathMatcher = new AntPathMatcher(); // 【声明定义】Ant路径匹配模式，“请求路径”和缓存中权限规则的“URL权限标识”匹配
//        String path = request.getURI().getPath();
//        String token = request.getHeaders().getFirst(AuthConstants.AUTHORIZATION_KEY);
//        // 移动端请求无需鉴权，只需认证（即JWT的验签和是否过期判断）
//        if (pathMatcher.match(GlobalConstants.APP_API_PATTERN, path)) {
//            // 如果token以"bearer "为前缀，到这一步说明是经过NimbusReactiveJwtDecoder#decode和JwtTimestampValidator#validate等解析和验证通过的，即已认证
//            if (StrUtil.isNotBlank(token) && token.startsWith(AuthConstants.AUTHORIZATION_PREFIX)) {
//                return Mono.just(new AuthorizationDecision(true));
//            } else {
//                return Mono.just(new AuthorizationDecision(false));
//            }
//        }
//        // Restful接口权限设计 @link https://www.cnblogs.com/haoxianrui/p/14396990.html
//        String restfulPath = request.getMethod() + ":" + path;
//        log.info("请求方法:RESTFul请求路径：{}", restfulPath);
//        // 缓存取【URL权限标识->角色集合】权限规则
//        Map<String, String> permRolesRules = redisTemplate.opsForHash().entries(GlobalConstants.URL_PERM_ROLES_KEY);
//        // 根据 “请求路径” 和 权限规则中的“URL权限标识”进行Ant匹配，得出拥有权限的角色集合
//        HashSet hasPermissionRoles = CollectionUtil.newHashSet(); // 【声明定义】有权限的角色集合
//        boolean needToCheck = false; // 【声明定义】是否需要被拦截检查的请求，如果缓存中权限规则中没有任何URL权限标识和此次请求的URL匹配，默认不需要被鉴权
//        for (Map.Entry<String,String> permRoles : permRolesRules.entrySet()) {
//            String perm = permRoles.getKey(); // 缓存权限规则的键：URL权限标识
//            if (pathMatcher.match(perm, restfulPath)) {
//                List roles = Convert.toList(String.class, permRoles.getValue()); // 缓存权限规则的值：有请求路径访问权限的角色集合
//                hasPermissionRoles.addAll(Convert.toList(String.class, roles));
//                if (needToCheck == false) {
//                    needToCheck = true;
//                }
//            }
//        }
//        // 没有设置权限规则放行；注：如果默认想拦截所有的请求请移除needToCheck变量逻辑即可，根据需求定制
//        if (needToCheck == false) {
//            return Mono.just(new AuthorizationDecision(true));
//        }
//        // 判断用户JWT中携带的角色是否有能通过权限拦截的角色
//        Mono authorizationDecisionMono = mono
//                .filter(Authentication::isAuthenticated)
//                .flatMapIterable(Authentication::getAuthorities)
//                .map(GrantedAuthority::getAuthority)
//                .any(authority -> {
//                    log.info("用户权限（角色） : {}", authority); // ROLE_ROOT
//                    String role = authority.substring(AuthConstants.AUTHORITY_PREFIX.length()); // 角色编码 ROOT
//                    if (GlobalConstants.ROOT_ROLE_CODE.equals(role)) { // 如果是超级管理员则放行
//                        return true;
//                    }
//                    return CollectionUtil.isNotEmpty(hasPermissionRoles) && hasPermissionRoles.contains(role); // 用户角色中只要有一个满足则通过权限校验
//                })
//                .map(AuthorizationDecision::new)
//                .defaultIfEmpty(new AuthorizationDecision(false));
//        return authorizationDecisionMono;
//    }
//
//    @Override
//    public Mono<Void> verify(Mono authentication, Object object) {
//        return ReactiveAuthorizationManager.super.verify(authentication, object);
//    }
}