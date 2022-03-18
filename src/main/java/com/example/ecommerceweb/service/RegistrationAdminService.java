package com.example.ecommerceweb.service;

import com.example.ecommerceweb.model.AdminDetail;
import com.example.ecommerceweb.model.Role;
import com.example.ecommerceweb.model.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationAdminService {
    private EmailValidator emailValidator;
    private AppUserService appUserService;
    public String registerAdmin(RegistrationRequest request) throws IllegalAccessException {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalAccessException("Email not valid");
        }
        return appUserService.signUpAdmin(new AdminDetail(
                request.getFirstName(),
                request.getFirstName(),
                request.getEmail(),
                request.getPassword(),
                Role.ADMIN
        ));
    }
}
