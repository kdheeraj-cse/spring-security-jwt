package org.dheeraj.springsecurityjwt.controller;

import org.dheeraj.springsecurityjwt.jwtUtil.JWTUtil;
import org.dheeraj.springsecurityjwt.model.AuthenticationRequest;
import org.dheeraj.springsecurityjwt.model.AuthenticationResponse;
import org.dheeraj.springsecurityjwt.model.MyUserDetails;
import org.dheeraj.springsecurityjwt.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    JWTUtil jwtUtil;

    @GetMapping("/")
    public String home(){
        return "<center><h1>Welcome All<h1><br/><a href='logout'>Logout</a></center>";
    }

    @GetMapping("/admin")
    public String adminHome(){
        return "<center><h1>Welcome Admin<h1><br/><a href='logout'>Logout</a></center>";
    }

    @GetMapping("/user")
    public String userhome(){
        return "<center><h1>Welcome User<h1><br/><a href='logout'>Logout</a></center>";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest request) throws Exception{

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new AuthenticationResponse(e.getMessage()));
        }

        MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(request.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(token));
        
    }

}
