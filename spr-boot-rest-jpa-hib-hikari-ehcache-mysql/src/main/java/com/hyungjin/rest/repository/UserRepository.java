package com.hyungjin.rest.repository;

import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hyungjin.rest.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Cacheable(value = "user")
	List<User> findAll();
	User findByUserId(String userId);
	User findByEmail(String email);
}
