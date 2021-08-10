package com.tekmentor.authenticationservice.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Data
@Configuration
public class CognitoConfiguration {
    private String awsClientId;

    private String userPool;

    private String awsSecretHash;

    private String awsAccessKey;

    private String awsSecretKey;

    @Profile("default")
    @Bean
    public CognitoConfiguration cognitoConfiguration(){
        this.awsClientId = System.getenv("awsClientId");
        this.userPool = System.getenv("userPool");
        this.awsSecretHash = System.getenv("awsSecretHash");
        this.awsAccessKey = System.getenv("awsAccessKey");
        this.awsSecretKey = System.getenv("awsSecretKey");
        return this;
    }

    @Bean
    public AWSCognitoIdentityProvider cognitoProvider(CognitoConfiguration configuration) {
        AWSCredentials cred = new BasicAWSCredentials(configuration.getAwsAccessKey(), configuration.getAwsSecretKey());
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(Regions.AP_SOUTH_1)
                .build();
    }
}
