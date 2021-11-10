package org.dheeraj.springsecurityjwt.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails{

    private String userName;
    private String password;
    private List<GrantedAuthority> roles;
    

    public MyUserDetails(String userName, String password, String roles){
        this.userName =  userName;
        this.password = password;
        this.roles = Arrays.asList(roles.split(",")).stream().map(p -> new SimpleGrantedAuthority(p)).collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
