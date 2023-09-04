package com.example.springbootthymeleafstudentmanagersystem.service.impl;

import com.example.springbootthymeleafstudentmanagersystem.dto.StudentDto;
import com.example.springbootthymeleafstudentmanagersystem.enties.Student;
import com.example.springbootthymeleafstudentmanagersystem.mapper.AutoMapper;
import com.example.springbootthymeleafstudentmanagersystem.repository.StudentRepository;
import com.example.springbootthymeleafstudentmanagersystem.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudenrServiceImpl implements StudentService {
    /**
     * @AllArgsConstructor để tự động autowired spring >4.3
     * sẽ sự autowired nếu chỉ có  1 constructor
     */
    private StudentRepository studentRepository;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(AutoMapper.MAPPER::toTarget).collect(Collectors.toList());
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        Student savedStudent = studentRepository.save(AutoMapper.MAPPER.toTarget(studentDto));
        return AutoMapper.MAPPER.toTarget(savedStudent);
    }

    @Override
    public StudentDto findStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.map(AutoMapper.MAPPER::toTarget).orElse(null);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        Student savedStudent = studentRepository.save(AutoMapper.MAPPER.toTarget(studentDto));
        return AutoMapper.MAPPER.toTarget(savedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
