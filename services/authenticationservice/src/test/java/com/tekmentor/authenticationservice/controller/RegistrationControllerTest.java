package com.tekmentor.authenticationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekmentor.authenticationservice.dto.SignupRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Profile("default")
class RegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    void registerUser() throws Exception {

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setGroup("User");
        signupRequest.setUsername("arunstiwari@gmail.com");
        signupRequest.setPassword("Arunstiwari123!");
        signupRequest.setPhoneNumber("+9195603094343");

        ObjectMapper objectMapper = new ObjectMapper();
        String signupR = objectMapper.writeValueAsString(signupRequest);
        mockMvc.perform(post("signup")
                .contentType("application/json")
                .content(signupR)
        ).andDo(print()).andExpect(status().isOk());

    }
}