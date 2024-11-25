package com.zhy.config;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.zhy.config.prop.AliyunProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: jobury
 * @Date: 2024/9/10 18:20
 */

@Component
public class OssClientConfig {

    @Autowired
    private AliyunProperties aliyunProperties;
    private volatile OSS ossClient;
    private volatile OSS ossClientExternal;
    public OSS getOssClient() {
        if (ossClient == null) {
            synchronized (OssClientConfig.class) {
                if(ossClient == null) {
                    CredentialsProvider credentialsProvider = new DefaultCredentialProvider(aliyunProperties.getAccessKey(), aliyunProperties.getAccessSecret());
                    ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
                    clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
                    ossClient = OSSClientBuilder.create()
                            .endpoint(aliyunProperties.getOssEndpointInternal())
                            .credentialsProvider(credentialsProvider)
                            .clientConfiguration(clientBuilderConfiguration)
                            .region(aliyunProperties.getOssRegion())
                            .build();
                }
            }
        }
        return ossClient;
    }
    public OSS getOssClientExternal() {
        if (ossClientExternal == null) {
            synchronized (OssClientConfig.class) {
                if(ossClientExternal == null) {
                    CredentialsProvider credentialsProvider = new DefaultCredentialProvider(aliyunProperties.getAccessKey(), aliyunProperties.getAccessSecret());
                    ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
                    clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
                    ossClientExternal = OSSClientBuilder.create()
                            .endpoint(aliyunProperties.getOssEndpointExternal())
                            .credentialsProvider(credentialsProvider)
                            .clientConfiguration(clientBuilderConfiguration)
                            .region(aliyunProperties.getOssRegion())
                            .build();
                }
            }
        }
        return ossClientExternal;
    }

}
