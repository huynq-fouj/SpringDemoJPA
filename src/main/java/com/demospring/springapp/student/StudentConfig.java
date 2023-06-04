package com.demospring.springapp.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student huy = new Student("Huy", "Huy@zz.zz", LocalDate.of(2002, 12, 20));
            Student linh = new Student("Linh", "linh123@gmail.com", LocalDate.of(2003, 6, 9));
            Student thanh = new Student("Thanh", "khongthihoaithanh@gmail.com", LocalDate.of(2003, 7, 1));
            repository.saveAll(List.of(huy, linh, thanh));
        };
    }
}
