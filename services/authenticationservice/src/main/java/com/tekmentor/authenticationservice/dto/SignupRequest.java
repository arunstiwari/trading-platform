package com.tekmentor.authenticationservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Data
public class SignupRequest {
    private String username;
    private String password;
    private String group;
    private String profileImage;
    private String phoneNumber;
}
