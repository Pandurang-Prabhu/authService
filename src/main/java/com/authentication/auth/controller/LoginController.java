package com.authentication.auth.controller;

import com.authentication.auth.model.CreateUserModel;
import com.authentication.auth.model.LoginModel;
import com.authentication.auth.model.LoginResponseModel;
import com.authentication.auth.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public String signup(@RequestBody CreateUserModel createUserModel){

        return  authenticationService.registerUser(createUserModel);
    }
    @PostMapping("/login")
    public LoginResponseModel loginUser(@RequestBody LoginModel loginModel) {
        return authenticationService.loginUser(loginModel);
    }
}
