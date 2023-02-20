package com.springCommerce.commerce.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springCommerce.commerce.user.model.UserExtraDetails;

public interface UserExtraDetailsRepository extends JpaRepository<UserExtraDetails, Long> {}