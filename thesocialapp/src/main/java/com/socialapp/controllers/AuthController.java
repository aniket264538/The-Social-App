package com.socialapp.controllers;

import com.socialapp.Security.JWTTokenHelper;
import com.socialapp.entities.User;
import com.socialapp.payloads.JWTAuthRequest;
import com.socialapp.payloads.JWTAuthResponse;
import com.socialapp.payloads.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:3000")
public class AuthController {

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> createToken(
            @RequestBody JWTAuthRequest request) throws Exception {

        this.authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

        String token = this.jwtTokenHelper.generateToken(userDetails);


        String username = userDetails.getUsername();
        JWTAuthResponse response = new JWTAuthResponse();
        response.setToken(token);
        response.setUser(this.modelMapper.map((User)userDetails, UserDto.class));

        System.out.println(response);

//        UserDetails Details = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//        String username = Details.getUsername();
//        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//        String username = loggedInUser.getDetails();
//        System.out.println(username);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);

        try {
            this.authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {

            System.out.println("Invalid Credentials!");

            throw new Exception("Invalid Username or Passsword!");

        }

    }

}
