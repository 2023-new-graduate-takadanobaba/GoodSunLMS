package com.reality.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reality.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
