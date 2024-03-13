package com.demospring.springapp.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.htmlparser.jericho.CharacterReference;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
	public List<Student> getStudents(){
		return studentService.getStudents();
	}

    @GetMapping("/allName")
    public String getMethodName() {
        StringBuilder allname = new StringBuilder();
        studentService.getStudents().forEach(item -> {
            allname.append(item.getName() + "<br>");
        });
        return allname.toString();
    }
    

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
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
