package com.migia.simpleLogin.controller;

import com.migia.simpleLogin.User.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class WebController {

    private UserService userService;



    public WebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/student")
    public String student(){
        return " Student module accessed";
    }
    @GetMapping("/admin")
    public String admin(){
        return " Student module accessed";
    }

    @GetMapping("/register/{name}/{password}")
    public String register(@PathVariable String name, @PathVariable String password){
        return userService.save(name, password);
    }

    @PostMapping("/welcome")
    public String signUp(Body body){

	System.out.println("Welcome module accessed " + body.getUsername() + "," + body.getPassword());
        return userService.save(body.getUsername(),body.getPassword());

    }



}

