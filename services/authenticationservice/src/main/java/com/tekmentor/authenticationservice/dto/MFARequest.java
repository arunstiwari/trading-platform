package com.tekmentor.authenticationservice.dto;

import lombok.Data;

@Data
public class MFARequest {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
