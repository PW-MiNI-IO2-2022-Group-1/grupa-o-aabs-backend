package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.Person;
import com.example.SOPSbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

// Spring boot automatically generates an implementation
// it also generates many simple functions such as findAll or findById
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM Doctor u WHERE u.email = :login AND u.password = :password", nativeQuery = true)
    User findByLoginPassword(@Param("login") String login, @Param("password") String password);
}
