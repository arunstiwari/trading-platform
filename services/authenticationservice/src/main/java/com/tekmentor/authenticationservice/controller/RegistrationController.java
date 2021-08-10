package com.tekmentor.authenticationservice.controller;

import com.amazonaws.services.cognitoidp.model.*;
import com.tekmentor.authenticationservice.dto.*;
import com.tekmentor.authenticationservice.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
public class RegistrationController {
    @Autowired
    private AuthenticationService authenticationservice;

    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody SignupRequest signupRequest){
        SignUpResult signUpResult = authenticationservice.signup(signupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(signupRequest);
    }

    @PostMapping("/confirmsignup")
    public ResponseEntity confirmSignUp(@RequestBody ConfirmSignup confirmSignUp){
        ConfirmSignUpResult confirmSignUpResult = authenticationservice.confirmSignUp(confirmSignUp);
        return ResponseEntity.status(HttpStatus.OK).body(confirmSignUpResult);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = authenticationservice.signIn(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @PostMapping("/mfalogin")
    public ResponseEntity mfalogin(@RequestBody LoginRequest loginRequest){
        AuthenticationResultType loginResponse = authenticationservice.mfaLogin(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @PostMapping("/mfa")
    public ResponseEntity mfaUpdate(@RequestBody MFARequest mfaRequest){
        SetUserMFAPreferenceResult setUserMFAPreferenceResult = authenticationservice.mfaUpdate(mfaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(setUserMFAPreferenceResult);
    }
    @PostMapping("/changepassword")
    public ResponseEntity mfaUpdate(@RequestBody PasswordChangeRequest passwordChangeRequest){
        PasswordChangeResponse response = authenticationservice.changePassword(passwordChangeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/phonenumber")
    public ResponseEntity mfaUpdate(@RequestBody PhoneNumberRequest phoneNumberRequest){
        AdminUpdateUserAttributesResult updateUserAttributesResult = authenticationservice.updatePhoneNumber(phoneNumberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateUserAttributesResult);
    }
}
