package com.example.hrm;

import com.example.hrm.entity.User;
import com.example.hrm.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrmApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("huongcaoha").isEmpty()) {
                User user = User.builder()
                        .username("huongcaoha")
                        .password(passwordEncoder.encode("huongcaoha123"))
                        .fullName("Nguyễn Công Hưởng")
                        .role("USER")
                        .enabled(true)
                        .build();
                userRepository.save(user);
            }
        };
    }
}