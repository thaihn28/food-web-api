package com.example.ecommerceweb.controller;

import com.example.ecommerceweb.model.Role;
import com.example.ecommerceweb.model.User;
import com.example.ecommerceweb.repository.RoleRepository;
import com.example.ecommerceweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
        return "login";
    }

    @GetMapping(value = "/register")
    public String registerGet(Model model){
            User user = new User();
//            List<User> companies = userRepository.findAll();
//            model.addAttribute("companies",companies);
            model.addAttribute("user", user);
        return "register";
    }
//    @RequestMapping(value = "/saveAdd")
//    public String saveAdd(
//            @Valid Employee employee, BindingResult result) {
//        if (result.hasErrors()) {
//            return "employeeAdd";
//        }
//        employeeRepository.save(employee);
//        return "redirect:/list";
//    }

    @PostMapping(value = "/register")
    public String registerPost(
            @ModelAttribute("user") User user, HttpServletRequest request,BindingResult result
            ) throws ServletException{
        if (result.hasErrors()) {
            return "register";
        }
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);
        userRepository.save(user);
        request.login(user.getEmail(),password);
        return "Redirect:/login";
    }
}
