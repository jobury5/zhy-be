package com.zhy.config;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.zhy.config.prop.AliyunProperties;
import darabonba.core.client.ClientOverrideConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsClientConfig {

    @Autowired
    private AliyunProperties aliyunProperties;

    private static final String ENDPOINT = "dysmsapi.aliyuncs.com";
    private static final String CONFIG_REGION = "cn-hangzhou";
    private volatile AsyncClient smsClient;

    public AsyncClient getSmsAsyncClient() {
        if (smsClient == null) {
            synchronized (SmsClientConfig.class) {
                if(smsClient == null) {
                    // Configure Credentials authentication information, including ak, secret, token
                    StaticCredentialProvider provider = StaticCredentialProvider.create(
                            Credential.builder().accessKeyId(aliyunProperties.getAccessKey()).accessKeySecret(aliyunProperties.getAccessSecret()).build());
                    // Configure the Client
                    smsClient = AsyncClient.builder().region(CONFIG_REGION).credentialsProvider(provider)
                            .overrideConfiguration(
                                    ClientOverrideConfiguration.create().setEndpointOverride(ENDPOINT)).build();
                }
            }
        }
        return smsClient;
    }


}
