package com.paygo.paygo.impl;

import com.paygo.paygo.config.UserInfoUserDetailsService;
import com.paygo.paygo.dto.LoginDto;
import com.paygo.paygo.entity.User;
import com.paygo.paygo.entity.UserProfile;
import com.paygo.paygo.repository.UserProfileRepository;
import com.paygo.paygo.repository.UserRepository;
import com.paygo.paygo.service.AuthService;
import com.paygo.paygo.service.JwtService;
import com.paygo.paygo.utils.AuthenticatedUser;
import com.paygo.paygo.utils.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoUserDetailsService userDetailsService;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private DataResponse response;

    @Autowired
    private AuthenticatedUser authenticatedUser;


    @Override
    public ResponseEntity<?> signInUser(LoginDto loginDto) {
        try {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());
            User user = userRepository.findUserByEmail(userDetails.getUsername());
            Map<Object, Object> data = new HashMap<>();
            Map<Object, Object> auth = new HashMap<>();

            final String token = jwtService.generateToken(userDetails.getUsername());
            try {
                authenticate(user.getEmail(), loginDto.getPassword());

                UserProfile userProfile = userProfileRepository.findUserProfileByUserId(user.getId());
                auth.put("accessToken", token);

                data.put("auth", auth);
                data.put("email", user.getEmail());
                data.put("firstName", userProfile.getFirstName());
                data.put("lastName", userProfile.getLastName());
                return response.dataResponse("00", "success", data, HttpStatus.OK);

            } catch (Exception e) {
                Map<Object, Object> error = new HashMap<>();
                error.put("message", e.getMessage());
                return response.dataResponse("99", "fail", error, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            Map<Object, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return response.dataResponse("99", "fail", error, HttpStatus.BAD_REQUEST);
        }
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    email, password));

        } catch (DisabledException e){
            throw new Exception("User disabled");
        } catch (BadCredentialsException e){
            throw new Exception("Bad Credentials");
        }
    }
}
