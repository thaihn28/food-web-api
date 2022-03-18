package com.example.ecommerceweb.service;

import com.example.ecommerceweb.model.Role;
import com.example.ecommerceweb.model.RegistrationRequest;
import com.example.ecommerceweb.model.UserDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationUserService {
    private EmailValidator emailValidator;
    private AppUserService appUserService;
    public String registerUser(RegistrationRequest request) throws IllegalAccessException {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalAccessException("Email not valid");
        }
        return appUserService.signUpUser(new UserDetail(
                request.getFirstName(),
                request.getFirstName(),
                request.getEmail(),
                request.getPassword(),
                Role.USER
        ));
    }
}
