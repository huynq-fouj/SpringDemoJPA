package com.demospring.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demospring.springapp.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    //Tạo truy vấn dựa trên phương thức
    //Student thay vì student vì đây k phải sql
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

}
