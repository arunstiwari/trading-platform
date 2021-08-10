package com.tekmentor.userservice.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class CognitoConfiguration {

    @Value("${aws.clientId}")
    private String awsClientId;

    @Value("${aws.userPoolId}")
    private String userPool;

    @Value("${aws.secretHash}")
    private String awsSecretHash;

    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Value("${aws.access_token_url}")
    private String accessTokenUrl;;

}
