package com.weather.weatheApi.service;

import com.weather.weatheApi.DTO.Users;
import com.weather.weatheApi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepo.findByUsername(username);
        if(user == null){
            System.out.println("User Not found!...");
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }

        return new UserPrincipal(user);
    }
}
