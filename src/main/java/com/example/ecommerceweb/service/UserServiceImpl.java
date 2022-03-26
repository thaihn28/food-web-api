package com.example.ecommerceweb.service;

import com.example.ecommerceweb.model.Role;
import com.example.ecommerceweb.model.User;
import com.example.ecommerceweb.repository.UserRepository;
import com.example.ecommerceweb.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private EmailValidator emailValidator;

    @Lazy
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(@Lazy UserRepository userRepository){
        super();
        this.userRepository=userRepository;
    }
    @Override
    public User saveUser(UserRegistrationDto registrationDto)  {
//         boolean isValidEmail = userRepository.findByEmail(registrationDto.getEmail()).isPresent();
//        if(!isValidEmail){
//            throw new IllegalAccessException("Email not valid");
//        }
        return userRepository.save(new User(registrationDto.getFirstName(),
                    registrationDto.getLastName(),
                    registrationDto.getEmail(),
                    passwordEncoder.encode(registrationDto.getPassword()),
                    Arrays.asList(new Role("ROLE_USER"))));
    }

    @Override
    public User saveAdmin(UserRegistrationDto registrationDto) {
//        User user = new User(registrationDto.getFirstName(),
//                registrationDto.getLastName(),
//                registrationDto.getEmail(),
//                passwordEncoder.encode(registrationDto.getPassword()),
//                Arrays.asList(new Role("ROLE_ADMIN")));
        return userRepository.save(new User(registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()),
                Arrays.asList(new Role("ROLE_ADMIN"))));
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).
                collect(Collectors.toList());
    }
}
