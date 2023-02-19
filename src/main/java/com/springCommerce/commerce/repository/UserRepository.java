package com.springCommerce.commerce.repository;

import com.springCommerce.commerce.model.BasicUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<BasicUser, Long> {

  Optional<BasicUser> findByMail(String mail);
}
