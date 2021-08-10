package com.tekmentor.stockservice.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Data
@Configuration
@Profile("default")
public class CognitoConfiguration {


    @Profile("default")
    @Bean
    public CognitoMetadata cognitoMetadata(){
        CognitoMetadata metadata = new CognitoMetadata();
        metadata.setAwsAccessKey(System.getenv("awsAccessKey"));
        metadata.setAwsClientId(System.getenv("awsClientId"));
        metadata.setRegion(System.getenv("region"));
        metadata.setUserPool(System.getenv("userPool"));
        metadata.setAwsSecretHash(System.getenv("awsSecretHash"));
        metadata.setAwsSecretKey(System.getenv("awsSecretKey"));
        log.info("metadata: {}",metadata);
        log.info("awsClientId: {} ",System.getenv("awsClientId"));
//        this.awsClientId = System.getenv("awsClientId");
//        this.userPool = System.getenv("userPool");
//        this.awsSecretHash = System.getenv("awsSecretHash");
//        this.awsAccessKey = System.getenv("awsAccessKey");
//        this.awsSecretKey = System.getenv("awsSecretKey");
//        this.region = System.getenv("region");
        return metadata;
    }

    @Profile("default")
    @Bean
    public AWSCognitoIdentityProvider cognitoProvider() {
        CognitoMetadata configuration = this.cognitoMetadata();
        log.info("configuration: {}",configuration);
        AWSCredentials cred = new BasicAWSCredentials(configuration.getAwsAccessKey(), configuration.getAwsSecretKey());
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(configuration.getRegion())
                .build();
    }
}
