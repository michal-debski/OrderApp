package pl.example.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Zwraca nazwę szablonu Thymeleaf dla strony logowania
    }

    @PostMapping("/login")
    public String LoginPage() {
        return "redirect:/";
    }


}
