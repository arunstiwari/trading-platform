package com.tekmentor.userservice.repository;

import com.tekmentor.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
