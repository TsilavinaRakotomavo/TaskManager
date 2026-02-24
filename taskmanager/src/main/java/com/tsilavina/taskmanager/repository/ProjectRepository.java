package com.tsilavina.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsilavina.taskmanager.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
