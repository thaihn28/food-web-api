package com.example.ecommerceweb.service;

import com.example.ecommerceweb.model.AdminDetail;
import com.example.ecommerceweb.model.UserDetail;
import com.example.ecommerceweb.repository.AdminRepository;
import com.example.ecommerceweb.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor

public class AppUserService implements UserDetailsService {
    private UserRepository userRepository;
    private AdminRepository adminRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final String USER_NOT_FOUND_MSG ="Email %s not fond";

//    public List<AppUserDetail> getAllAccount(){
//        return appUserRepository.findAll();
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return (UserDetails) userRepository.findByEmail(email).
                orElseThrow(() ->
                    new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG,email)));
    }

    public String signUpUser(UserDetail user) {
        boolean userExists = userRepository.findByEmail(user.getEmail())
                .isPresent();
        String result = "";
        if(userExists){
//            throw new IllegalAccessException("Email already taken");
            result = "Email already exist";
        }else {
            String encodePassword = bCryptPasswordEncoder.
                    encode(user.getPassword());
            user.setPassword(encodePassword);
            userRepository.save(user);

            result =  "Sign Up Successfully";
        }
        return result;
    }

    public String signUpAdmin(AdminDetail admin) {
        boolean userExists = adminRepository.findByEmail(admin.getEmail())
                .isPresent();
        String result = "";
        if(userExists){
//            throw new IllegalAccessException("Email already taken");
            result = "Email already exist";
        }else {
            String encodePassword = bCryptPasswordEncoder.
                    encode(admin.getPassword());
            admin.setPassword(encodePassword);
            adminRepository.save(admin);

            result =  "Sign Up Successfully";
        }
        return result;
    }
}
