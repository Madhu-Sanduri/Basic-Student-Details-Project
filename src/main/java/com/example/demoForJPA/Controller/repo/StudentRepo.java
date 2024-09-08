package com.example.demoForJPA.Controller.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoForJPA.Entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long>{

}
