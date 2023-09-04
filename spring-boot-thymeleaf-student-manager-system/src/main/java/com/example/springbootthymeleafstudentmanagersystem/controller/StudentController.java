package com.example.springbootthymeleafstudentmanagersystem.controller;


import com.example.springbootthymeleafstudentmanagersystem.dto.StudentDto;
import com.example.springbootthymeleafstudentmanagersystem.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;

    @GetMapping("/students")
    public String getAllStudent(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new StudentDto());
        return "create_student";
    }

    @PostMapping("/student")
    public String saveStudent(Model model, @Valid @ModelAttribute("student") StudentDto studentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("student", studentDto);
            return "create_student";
        }
        studentService.addStudent(studentDto);
        return "redirect:/students";
    }
    @GetMapping("/students/{id}/edit")
    public String editStudent(@PathVariable Long id, Model model){
        StudentDto student = studentService.findStudentById(id);
        model.addAttribute("student",student);
        return "edit_student";
    }
    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,Model model, @Valid @ModelAttribute("student") StudentDto studentDto, BindingResult bindingResult) {
        System.out.println(studentDto);
        if (bindingResult.hasErrors()) {
            model.addAttribute("student", studentDto);
            return "edit_student";
        }
//        cho nay code du
//        studentDto.setId(id);
        studentService.updateStudent(studentDto);
        return "redirect:/students";
    }
    @GetMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

}
