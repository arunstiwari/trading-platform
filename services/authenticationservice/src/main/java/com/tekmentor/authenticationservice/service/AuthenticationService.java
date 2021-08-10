package com.tekmentor.authenticationservice.service;

//import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.*;
import com.tekmentor.authenticationservice.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class AuthenticationService {

    @Autowired
    private CognitoService cognitoService;

    public SignUpResult signup(SignupRequest signUpRequest) {
        SignUpResult signUpResult = cognitoService.signUp(signUpRequest);

        //Here make a call to User service to persist the user in database
        // @TODO
        return signUpResult;
    }

    public LoginResponse signIn(LoginRequest signInRequest) {
        Map<String, String> loginResult = cognitoService.login(signInRequest);
        log.info("Login Result : {}",loginResult);
        LoginResponse response = new LoginResponse();
        response.setMessage("User Signed up successfully");
        response.setTokens(loginResult);
        return response;
    }

    public ConfirmSignUpResult confirmSignUp(ConfirmSignup confirmSignUp){
         return cognitoService.confirmSignUp(confirmSignUp);
    }

    public SetUserMFAPreferenceResult mfaUpdate(MFARequest mfaRequest) {
            return cognitoService.mfaUpdate(mfaRequest);
    }

    public PasswordChangeResponse changePassword(PasswordChangeRequest passwordChangeRequest) {
        return cognitoService.changePassword(passwordChangeRequest);
    }

    public AdminUpdateUserAttributesResult updatePhoneNumber(PhoneNumberRequest phoneNumberRequest){
        return cognitoService.updatePhoneNumber(phoneNumberRequest);
    }

    public AuthenticationResultType mfaLogin(LoginRequest loginRequest) {
        return cognitoService.mfaLogin(loginRequest.getUsername(),loginRequest.getConfirmationCode(),loginRequest.getSession());
    }
}
