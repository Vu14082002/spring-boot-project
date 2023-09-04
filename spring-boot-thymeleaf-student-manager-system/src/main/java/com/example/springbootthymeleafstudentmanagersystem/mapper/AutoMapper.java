package com.example.springbootthymeleafstudentmanagersystem.mapper;

import com.example.springbootthymeleafstudentmanagersystem.dto.StudentDto;
import com.example.springbootthymeleafstudentmanagersystem.enties.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoMapper {

    AutoMapper MAPPER = Mappers.getMapper(AutoMapper.class);

    StudentDto toTarget(Student student);
    Student toTarget(StudentDto studentDto);
}
