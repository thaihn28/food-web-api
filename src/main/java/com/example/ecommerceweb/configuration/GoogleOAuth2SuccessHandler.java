package com.example.ecommerceweb.configuration;

import com.example.ecommerceweb.model.Role;
import com.example.ecommerceweb.model.User;
import com.example.ecommerceweb.repository.RoleRepository;
import com.example.ecommerceweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String username = token.getPrincipal().getAttributes().get("username").toString();
        if(userRepository.findUserByUsername(username).isPresent()){

        }else{
            User user = new User();
            user.setUsername(username);
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findById(2L).get());
            user.setRoles(roles);
            userRepository.save(user);
        }
        redirectStrategy.sendRedirect(request,response,"/");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
    }
}
