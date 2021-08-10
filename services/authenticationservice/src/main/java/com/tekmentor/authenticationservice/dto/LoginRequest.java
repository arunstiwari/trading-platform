package com.tekmentor.authenticationservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class LoginRequest {
    private String username;
    private String password;
    private String confirmationCode;
    private String session;
}
