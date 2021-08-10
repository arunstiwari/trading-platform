package com.tekmentor.stockservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DatabaseConfiguration {
    private Gson gson = new Gson();

    @Autowired
    private Environment env;

    @Profile("cloud")
    @Bean
    public DataSource dataSource() {
        DataSourceMetadata db = getDataSourceMetadata();
        log.info("db : {}",db);
        String dataSourceUrl = "jdbc:postgresql://"+db.getHost()+":"+db.getPort()+"/";
        log.info(" dataSourceUrl : {}",dataSourceUrl);
        return DataSourceBuilder.create()
                .url(dataSourceUrl)
                .username(db.getUsername())
                .password(db.getPassword())
                .build();
    }

    @Profile({"docker"})
    @Bean
    public DataSource dataSourceLocal() {
        log.info(" env: {} ",env);
        DataSourceMetadata db = new DataSourceMetadata();
        String dataSourceUrl = System.getenv("datasource.url");
        String username = System.getenv("datasource.username");
        String password = System.getenv("datasource.password");
        log.info("url : {}, username: {}",dataSourceUrl, username);

        return DataSourceBuilder.create()
                .url(dataSourceUrl)
                .username(username)
                .password(password)
                .build();
    }

    private DataSourceMetadata getDataSourceMetadata() {
        String secretName = System.getenv("databaseSecret");
        String region = System.getenv("region");
        String awsAccessKey = System.getenv("awsAccessKey");
        String awsSecretKey = System.getenv("awsSecretKey");

        log.info(" secretName: {}, region: {}",secretName,region);

        // Create a Secret Manager Client
        AWSSecretsManager client = AWSSecretsManagerClientBuilder
                                        .standard()
                                        .withRegion(region)
                                        .withCredentials(
                                                new AWSStaticCredentialsProvider(
                                                        new BasicAWSCredentials(awsAccessKey,awsSecretKey)
                                                )
                                        ).build();

        String secret, decodedBinarySecret;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = null;
        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        }catch (Exception e) {
            log.error("Error getting secret value {}",e);
            throw e;
        }

        if (getSecretValueResult.getSecretString() !=null){
            secret = getSecretValueResult.getSecretString();
            return gson.fromJson(secret,DataSourceMetadata.class);
        }

        return null;
    }
}
