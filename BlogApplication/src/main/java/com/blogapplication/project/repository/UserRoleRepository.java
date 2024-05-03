package com.blogapplication.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.project.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{

}
