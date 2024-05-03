package com.blogapplication.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.project.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	 UserEntity findByEmail(String email);
}
