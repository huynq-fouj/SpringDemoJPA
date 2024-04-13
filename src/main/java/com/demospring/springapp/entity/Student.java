package com.demospring.springapp.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private LocalDate dod;

    public Student() {
    }

    public Student(Long id, String name, String email, LocalDate dod) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dod = dod;
    }
    
    public Student(String name, String email, LocalDate dod) {
        this.name = name;
        this.email = email;
        this.dod = dod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDod() {
        return dod;
    }

    public void setDod(LocalDate dod) {
        this.dod = dod;
    }

   
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + ", dod=" + dod + "]";
    }
    
}
