package com.tekmentor.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeResponse {
    private String message;

    public PasswordChangeResponse withMessage(String message) {
        setMessage(message);
        return this;
    }
}
