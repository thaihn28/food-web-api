package com.example.ecommerceweb.controller;

import com.example.ecommerceweb.model.Role;
import com.example.ecommerceweb.model.User;
import com.example.ecommerceweb.repository.RoleRepository;
import com.example.ecommerceweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping(value="/login")
    public String login(){
        return "assets/login";
    }

    @GetMapping(value = "/register")
    public String registerGet(){
        return "assets/register";
    }

    @PostMapping(value = "/register")
    public String registerPost(
            @ModelAttribute("user") User user, HttpServletRequest request
            ) throws ServletException{
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2L).get());
        user.setRoles(roles);
        userRepository.save(user);
        request.login(user.getUsername(),password);
        return "Redirect:/admin";
    }
}
