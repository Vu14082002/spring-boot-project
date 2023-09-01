package com.edu.iuh.fit.springboot.repository;

import com.edu.iuh.fit.springboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
