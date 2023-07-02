package com.example.kakaoshop.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserJPARepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(String username, String password, String email) {
        User user = User.builder().username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .roles("ROLE_USER").build();

        userRepository.save(user);

        return "redirect:/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }
}
