package com.example.springbootthymeleafstudentmanagersystem.repository;

import com.example.springbootthymeleafstudentmanagersystem.enties.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//@Repository -- khong cna chi dinh vi de ke thua JpaRepository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
