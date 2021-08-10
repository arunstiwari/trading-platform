package com.tekmentor.authenticationservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Base64;

@Slf4j
@Configuration
public class AWSSecretsConfiguration {

    private Gson gson = new Gson();

    @Profile("cloud")
    @Bean
    public CognitoConfiguration configuration(){
        CognitoConfiguration config = secret();
        log.info("config : {}",config);
        return config;
    }

    private CognitoConfiguration secret(){
        String secretName = "dev/cloud/tradeapp";
        String region = "ap-south-1";
        String accessKey = System.getenv("awsAccessKey");
        String secretKey = System.getenv("awsSecretKey");
        log.info("accessKey: {}, secretKey: {}",accessKey, secretKey);
        // Create a Secrets Manager client
        AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey,secretKey)))
                .build();


        String secret, decodedBinarySecret;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = null;

        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            log.error("Error getting secret value {}",e);
            throw e;
        }

        if (getSecretValueResult.getSecretString() != null) {
            secret = getSecretValueResult.getSecretString();
            log.info("secret: {}",secret);
            return gson.fromJson(secret,CognitoConfiguration.class);
        }
        else {
            decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
            log.info("decodedBinarySecret : {}",decodedBinarySecret);
        }

        return null;
    }
}
