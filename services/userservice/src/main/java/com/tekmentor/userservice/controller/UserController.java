package com.tekmentor.userservice.controller;

import com.tekmentor.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_users/users.read')")
    public ResponseEntity fetchUsers(){
       log.info("Inside fetchUsers: ");
        Object stocks = userService.getStocks();
        log.info("stocks : {}",stocks);
        return ResponseEntity.ok().body("Users fetched");
    }

}
