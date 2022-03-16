package com.example.SOPSbackend.security;

import com.example.SOPSbackend.model.Doctor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class UserEntityDetails implements UserDetails {
    private String email;
    private String password;
    private Role role;

    public UserEntityDetails(Doctor doctor) {
        this.email = doctor.getEmail();
        this.password = doctor.getPassword();
        this.role = Role.DOCTOR;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var grantedAuthority = new SimpleGrantedAuthority(role.getRoleToString());
        return new HashSet<GrantedAuthority>(Arrays.asList(grantedAuthority));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
