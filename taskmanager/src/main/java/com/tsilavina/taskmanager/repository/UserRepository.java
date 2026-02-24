package com.tsilavina.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsilavina.taskmanager.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}