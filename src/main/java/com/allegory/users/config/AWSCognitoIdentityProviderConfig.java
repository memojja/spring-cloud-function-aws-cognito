package com.allegory.users.config;

import com.allegory.users.constant.CognitoConfig;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AWSCognitoIdentityProviderConfig {

    @Bean
    public AWSCognitoIdentityProvider createAWSCognitoIdentityProvider() {
        BasicAWSCredentials creds = new BasicAWSCredentials(CognitoConfig.AWS_ACCESS_KEY, CognitoConfig.AWS_SECRET_KEY);
        AWSCognitoIdentityProviderClientBuilder builder
                = AWSCognitoIdentityProviderClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds));
        builder.setRegion(CognitoConfig.AWS_REGION);
        return builder.build();
    }

}
