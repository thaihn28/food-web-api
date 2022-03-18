//package com.example.ecommerceweb.service;
//
////import com.example.ecommerceweb.model.ConfirmationToken;
//import com.example.ecommerceweb.model.CustomUserDetail;
//import com.example.ecommerceweb.model.User;
//import com.example.ecommerceweb.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//    private final static String USER_NOT_FOUND_MSG =
//            "Email %s not found";
////    @Autowired
////    private BCryptPasswordEncoder bCryptPasswordEncoder;
////    @Autowired
////    private ConfirmationTokenService confirmationTokenService;
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findUserByEmail(email);
//        user.orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
//        return user.map(CustomUserDetail::new).get();
//    }
//
////    public String signUpUser(User user) {
////        boolean userExists = userRepository
////                .findUserByEmail(user.getEmail())
////                .isPresent();
////
////        if (userExists) {
////            // TODO check of attributes are the same and
////            // TODO if email not confirmed send confirmation email.
////
////            throw new IllegalStateException("email already taken");
////        }
////
////        String encodedPassword = bCryptPasswordEncoder
////                .encode(user.getPassword());
////
////        user.setPassword(encodedPassword);
////
////        userRepository.save(user);
////
////        String token = UUID.randomUUID().toString();
////
////        ConfirmationToken confirmationToken = new ConfirmationToken(
////                token,
////                LocalDateTime.now(),
////                LocalDateTime.now().plusMinutes(15),
////                user
////        );
////
////        confirmationTokenService.saveConfirmationToken(confirmationToken);
////
//////        TODO: SEND EMAIL
////
////        return token;
////    }
////
////    public int enableAppUser(String email) {
////        return userRepository.enableAppUser(email);
////    }
//}
