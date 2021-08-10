package com.tekmentor.userservice.service;

import com.tekmentor.userservice.config.CognitoConfiguration;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    private CognitoConfiguration configuration;

    public Object getStocks( ){
        RestTemplate restTemplate = new RestTemplate();
        String access_token_url = getAccessTokenUrl();
        HttpEntity request = getHttpEntity();
        ResponseEntity<String> response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);

        System.out.println("Access Token Response ---------" + response.getBody());

        return response.getBody();
    }

    private String getAccessTokenUrl(){
        String access_token_url = configuration.getAccessTokenUrl();
        access_token_url += "?grant_type=client_credentials";
        return access_token_url;
    }

    private HttpEntity getHttpEntity(){
        String credentials = configuration.getAwsClientId()+":"+configuration.getAwsSecretHash();
        //"29mhjtko1m794ankc6hr5epf7d:unjirl6mts1csskhha73eocsbpjejdpi9asfofpapafhtc19sv4";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic " + encodedCredentials);
       return new HttpEntity<String>(headers);
    }
}
