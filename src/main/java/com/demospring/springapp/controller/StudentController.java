package com.demospring.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demospring.springapp.dto.RegisterStudent;
import com.demospring.springapp.entity.Student;
import com.demospring.springapp.service.StudentService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import net.htmlparser.jericho.CharacterReference;

@RestController
@RequestMapping(path = "api/v1/student")
@Validated
public class StudentController {
    
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
	public List<Student> getStudents(
        @Min(value = 10, message = "Page size must greater than 9") 
        @Max(value = 50, message = "Page size must be less than 51") int pageSize, 
        @Positive(message = "Page number must be greater than 0") int pageNum){
		return studentService.getStudents();
	}
    

    @PostMapping
    public void registerNewStudent(@RequestBody RegisterStudent student){
        Student newStudent = new Student();
        newStudent.setEmail(student.getEmail());
        newStudent.setName(CharacterReference.encode(student.getName()));
        newStudent.setDod(student.getDod());
        studentService.addNewStudent(newStudent);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, name, email);
    }

    
}
