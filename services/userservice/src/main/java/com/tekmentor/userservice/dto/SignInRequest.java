package com.tekmentor.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class SignInRequest {
    private String username;
    private String password;
}
