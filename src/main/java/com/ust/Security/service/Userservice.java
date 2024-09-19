package com.ust.Security.service;

import com.ust.Security.model.Userinfo;
import com.ust.Security.repository.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Userservice {
    @Autowired
    private UserinfoRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String saveUser(Userinfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repo.save(userInfo);
        return "user added to system ";
    }

}
