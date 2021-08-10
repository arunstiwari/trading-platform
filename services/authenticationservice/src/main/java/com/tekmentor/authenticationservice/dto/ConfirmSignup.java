package com.tekmentor.authenticationservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ConfirmSignup {

    private String username;
    private String confirmationCode;

}
