package com.tekmentor.userservice.service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.tekmentor.userservice.config.CognitoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//@Service
public class CognitoService {

    @Autowired
    private AWSCognitoIdentityProvider cognitoProvider;

    @Autowired
    private CognitoConfiguration configuration;


    public SignUpResult signUp(String email, String password, List<AttributeType> userAttributes) {
        String secretHash = calculateSecretHash(configuration.getAwsClientId(), configuration.getAwsSecretHash(),email);
        SignUpRequest request = new SignUpRequest().withClientId(configuration.getAwsClientId() )
                                                .withUsername(email)
                                                .withPassword(password)
                                                .withUserAttributes(userAttributes)
                                                .withSecretHash(secretHash);

        SignUpResult result = cognitoProvider.signUp(request);
        AdminAddUserToGroupRequest adminAddUserToGroupRequest = new AdminAddUserToGroupRequest()
                                                                .withUserPoolId(configuration.getUserPool())
                                                                .withUsername(email)
                                                                .withGroupName("User");
        cognitoProvider.adminAddUserToGroup(adminAddUserToGroupRequest);
        return result;
    }

    public ConfirmSignUpResult confirmSignUp(String email, String confirmationCode) {
        ConfirmSignUpRequest confirmSignUpRequest = new ConfirmSignUpRequest()
                                                            .withClientId(configuration.getAwsClientId())
                                                            .withUsername(email)
                                                            .withConfirmationCode(confirmationCode);
        return cognitoProvider.confirmSignUp(confirmSignUpRequest);
    }

    public Map<String, String> login(String email, String password) {
        String secretHash = calculateSecretHash(configuration.getAwsClientId(), configuration.getAwsSecretHash(), email);
        Map<String, String> authParams = new LinkedHashMap<String, String>() {{
            put("USERNAME", email);
            put("PASSWORD", password);
            put("SECRET_HASH",secretHash);
        }};

        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withUserPoolId(configuration.getUserPool())
                .withClientId(configuration.getAwsClientId())
                .withAuthParameters(authParams);

        AdminInitiateAuthResult authResult = cognitoProvider.adminInitiateAuth(authRequest);
        AuthenticationResultType resultType = authResult.getAuthenticationResult();

//        GetUserRequest userRequest = new GetUserRequest().;
//        cognitoProvider.getUser(userRequest);
        return new LinkedHashMap<String, String>() {{
            put("idToken", resultType.getIdToken());
            put("accessToken", resultType.getAccessToken());
            put("refreshToken", resultType.getRefreshToken());
            put("message", "Successfully login");
        }};
    }
    //https://cognito-idp.ap-south-1.amazonaws.com/ap-south-1_FdoTQYVkk/.well-known/jwks.json

    public static String calculateSecretHash(String userPoolClientId, String userPoolClientSecret, String userName) {
        final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

        SecretKeySpec signingKey = new SecretKeySpec(
                userPoolClientSecret.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            mac.update(userName.getBytes(StandardCharsets.UTF_8));
            byte[] rawHmac = mac.doFinal(userPoolClientId.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating ");
        }
    }
}
