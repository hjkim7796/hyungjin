package com.hyungjin.rest.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hyungjin.rest.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	@Override
	@Cacheable(value = "user")
	List<User> findAll();
	User findOne(String userId);
	User findByEmail(String email);
}
