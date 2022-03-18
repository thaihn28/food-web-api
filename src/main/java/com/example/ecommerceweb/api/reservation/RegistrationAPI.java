package com.example.ecommerceweb.api.reservation;

import com.example.ecommerceweb.model.RegistrationRequest;
import com.example.ecommerceweb.repository.UserRepository;
import com.example.ecommerceweb.service.AppUserService;
import com.example.ecommerceweb.service.RegistrationAdminService;
import com.example.ecommerceweb.service.RegistrationUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(path = "/api/registration")
@AllArgsConstructor
public class RegistrationAPI {

    @Autowired
    private UserRepository userRepository;
    private AppUserService appUserService;
    private RegistrationUserService registrationService;
    private RegistrationAdminService registrationAdmin;

    @PostMapping(value = "/user-registration")
     public String registerUser(@RequestBody RegistrationRequest request) throws IllegalAccessException {
         return registrationService.registerUser(request);
     }

    @PostMapping(value = "/admin-registration")
    public String registerAdmin(@RequestBody RegistrationRequest request) throws IllegalAccessException {
        return registrationAdmin.registerAdmin(request);
    }



}
