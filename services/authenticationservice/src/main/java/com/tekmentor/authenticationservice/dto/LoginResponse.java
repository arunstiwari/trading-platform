package com.tekmentor.authenticationservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
@NoArgsConstructor
public class LoginResponse {
    private String message;
    private Map<String, String> tokens;
}
