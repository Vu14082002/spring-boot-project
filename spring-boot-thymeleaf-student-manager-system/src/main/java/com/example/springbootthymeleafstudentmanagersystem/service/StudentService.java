package com.example.springbootthymeleafstudentmanagersystem.service;

import com.example.springbootthymeleafstudentmanagersystem.dto.StudentDto;
import com.example.springbootthymeleafstudentmanagersystem.enties.Student;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();
    StudentDto addStudent(StudentDto studentDto);
    StudentDto findStudentById(Long id);
    StudentDto updateStudent(StudentDto studentDto);

    void deleteStudent(Long id);

}
