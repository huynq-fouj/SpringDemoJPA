package com.demospring.springapp.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demospring.springapp.entity.Student;
import com.demospring.springapp.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
		return studentRepository.findAll();
	}

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    public boolean isExists(Long id){
        return studentRepository.existsById(id);
    }

    public void deleteStudent(Long studentId) {
        if(!isExists(studentId)){
            throw new IllegalStateException("Not exists student");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new IllegalStateException(
                "Not exists student id"));
        if(name != null && name.length() > 0 && !Objects.equals(name, student.getName())){
            student.setName(name);
        }
        if(email != null && email.length() > 0 && !Objects.equals(email, student.getEmail())){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }
    }
}