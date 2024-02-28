package pl.example.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.example.infrastructure.security.RegistrationRequest;
import pl.example.infrastructure.security.UserService;

@Controller
@RequestMapping("/register")
@AllArgsConstructor

public class RegistrationController {

    private final UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "registration-form";
    }

    @PostMapping("/register")
    public String registerClient(@Valid @ModelAttribute("registrationRequest") RegistrationRequest request,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }

        userService.registerUser(request);
        redirectAttributes.addFlashAttribute("registrationSuccess", true);
        return "redirect:/login";
    }
}