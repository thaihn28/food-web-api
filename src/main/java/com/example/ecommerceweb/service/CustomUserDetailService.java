package com.example.ecommerceweb.service;

import com.example.ecommerceweb.model.CustomUserDetail;
import com.example.ecommerceweb.model.User;
import com.example.ecommerceweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        user.orElseThrow(()->new UsernameNotFoundException("User to fail"));
        return user.map(CustomUserDetail::new).get();
    }
}
