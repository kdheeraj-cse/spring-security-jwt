package org.dheeraj.springsecurityjwt.service;

import java.util.Optional;

import org.dheeraj.springsecurityjwt.entity.UserEntity;
import org.dheeraj.springsecurityjwt.model.MyUserDetails;
import org.dheeraj.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<UserEntity> users =  userRepository.findByUsername(username);

        users.orElseThrow(()->{
            throw new UsernameNotFoundException(username +" is not a valid user");
        });

        return new MyUserDetails(username, users.get().getPassword(), users.get().getRoles());
    }

}
