package com.example.springbootthymeleafstudentmanagersystem.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDto {
    private Long id;
    @NotEmpty(message = "Student firstname should not be empty")
    private String firstname;
    @NotEmpty(message = "Student lastname should not be empty")
    private String lastname;
    @NotEmpty(message = "Student email should not be empty")
    @Email
    private String email;
}
