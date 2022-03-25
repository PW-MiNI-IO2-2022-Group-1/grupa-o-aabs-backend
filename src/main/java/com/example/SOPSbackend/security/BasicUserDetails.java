package com.example.SOPSbackend.security;

import com.example.SOPSbackend.model.BasicUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Getter
public class BasicUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final Role role;

    private final BasicUser user;

    public BasicUserDetails(BasicUser user) {
        username = user.getEmail();
        password = user.getPassword();
        role = user.getRole();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var grantedAuthority = new SimpleGrantedAuthority(role.getLabel());
        return new HashSet<GrantedAuthority>(List.of(grantedAuthority));
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
