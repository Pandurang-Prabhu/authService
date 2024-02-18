package com.authentication.auth.controller;

import com.authentication.auth.model.LoginModel;
import com.authentication.auth.model.LoginResponseModel;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class testController {

    @GetMapping("/ff")
    public String f() {
        return "fdfsdf";
    }

}
