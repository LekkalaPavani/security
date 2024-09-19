package com.ust.Security.controller;

import com.ust.Security.dto.AuthRequest;
import com.ust.Security.model.Job;
import com.ust.Security.model.Userinfo;
import com.ust.Security.service.Jobservice;
import com.ust.Security.service.JwtService;
import com.ust.Security.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private Userservice userService;
    @Autowired
    private Jobservice jobService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/adduser")
    public String addUser(@RequestBody Userinfo user) {

        return userService.saveUser(user);
    }

    @PostMapping("/addjob")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Job addJob(@RequestBody Job job) {

        return jobService.saveJob(job);
    }

    @GetMapping("/getalljobs")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }



}
