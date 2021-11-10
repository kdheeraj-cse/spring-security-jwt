package org.dheeraj.springsecurityjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public String home(){
        return "<center><h1>Welcome<h1><br/><a href='logout'>Logout</a></center>";
    }
}
