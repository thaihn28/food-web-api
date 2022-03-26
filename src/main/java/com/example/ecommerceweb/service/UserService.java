package com.example.ecommerceweb.service;

import com.example.ecommerceweb.model.User;
import com.example.ecommerceweb.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

//@Service
public interface UserService  extends UserDetailsService {
    User saveUser(UserRegistrationDto registrationDto) ;
    User saveAdmin(UserRegistrationDto registrationDto);
}
