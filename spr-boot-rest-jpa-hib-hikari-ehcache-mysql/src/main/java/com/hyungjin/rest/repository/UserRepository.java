package com.hyungjin.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyungjin.rest.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);
}
