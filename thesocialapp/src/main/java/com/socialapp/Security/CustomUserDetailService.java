package com.socialapp.Security;

import com.socialapp.entities.User;
import com.socialapp.exceptions.ResourceNotFoundException;
import com.socialapp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Loading user from database
        User user = this.userRepo.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("User", "username", username));

        return user;
    }


}
