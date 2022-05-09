package com.example.SOPSbackend.security;

import com.example.SOPSbackend.model.BasicUserEntity;
import com.example.SOPSbackend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


public class BasicUserService<T extends BasicUserEntity> implements UserDetailsService {

    private UserRepository<T, Long> repository;

    public BasicUserService(UserRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public BasicUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        T user = repository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("No such user"));
        return new BasicUserDetails(user);
    }
}
