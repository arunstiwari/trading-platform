package com.tekmentor.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
@NoArgsConstructor
public class SignInResponse {
    private String message;
    private Map<String, String> tokens;
}
