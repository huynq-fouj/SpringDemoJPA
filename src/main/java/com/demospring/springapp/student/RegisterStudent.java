package com.demospring.springapp.student;

import java.time.LocalDate;


public class RegisterStudent {
    private String name;
    private String email;
    private LocalDate dod;
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
    
}
