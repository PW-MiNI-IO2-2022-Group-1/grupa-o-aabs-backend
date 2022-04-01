package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.BasicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface UserRepository<T extends BasicUser, ID extends Serializable> extends JpaRepository<T, ID> {
    Optional<T> findByEmailIgnoreCase(String email);
}
