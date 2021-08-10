package com.tekmentor.stockservice.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class CognitoMetadata {
    private String awsClientId;

    private String userPool;

    private String awsSecretHash;

    private String awsAccessKey;

    private String awsSecretKey;

    private String region;
}
