package com.example.SOPSbackend.security;

import com.example.SOPSbackend.model.Admin;
import com.example.SOPSbackend.model.BasicUser;
import com.example.SOPSbackend.model.Doctor;
import com.example.SOPSbackend.model.Patient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class BasicUserDetails implements UserDetails {
    private final String email;
    private final String password;
    private final Role role;

    private final Object databaseObject;

    public BasicUserDetails(BasicUser user) {
        email = user.getEmail();
        password = user.getPassword();
        role = user.getRole();
        databaseObject = user;
    }

    public Role getRole() {
        return role;
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    public boolean isDoctor() {
        return role == Role.DOCTOR;
    }

    public boolean isPatient() {
        return role == Role.PATIENT;
    }

    public Admin asAdmin() {
        if(isAdmin())
            return (Admin)databaseObject;
        else
            throw new ClassCastException();
    }

    public Doctor asDoctor() {
        if(isDoctor())
            return (Doctor)databaseObject;
        else
            throw new ClassCastException();
    }

    public Patient asPatient() {
        if(isAdmin())
            return (Patient)databaseObject;
        else
            throw new ClassCastException();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var grantedAuthority = new SimpleGrantedAuthority(role.getRoleToString());
        return new HashSet<GrantedAuthority>(List.of(grantedAuthority));
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
