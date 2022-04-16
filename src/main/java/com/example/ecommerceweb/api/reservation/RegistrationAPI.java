package com.example.ecommerceweb.api.reservation;

import com.example.ecommerceweb.dto.UserRegistrationDto;
import com.example.ecommerceweb.model.User;
import com.example.ecommerceweb.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
////@RequestMapping(path = "/api/registration")
//@AllArgsConstructor
public class RegistrationAPI {
    @Autowired
    UserServiceImpl userService ;

//    UserRegistrationDto userRegistrationDto;
    @PostMapping(value = "/api/registrationUser")
    public User registrationUser(@RequestBody UserRegistrationDto userRegistrationDto) {
//        userService.saveUser(userRegistrationDto);
        return userService.saveUser(userRegistrationDto);
    }
    @PostMapping(value = "/api/registrationAdmin")
    public User registrationAdmin(@RequestBody UserRegistrationDto userRegistrationDto) {
//        userService.saveUser(userRegistrationDto);
        return userService.saveAdmin(userRegistrationDto);
    }
}



