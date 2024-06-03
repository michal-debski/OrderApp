package pl.example.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.example.api.dto.UserDTO;
import pl.example.api.dto.mapper.UserMapper;
import pl.example.business.UserService;
import pl.example.domain.User;

import java.util.Objects;


@Controller
@Slf4j
@AllArgsConstructor
public class LoginController {
    private final UserService userService;

    private final UserMapper userMapper;
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
//    @PostMapping("/login")
//    public String processLogin(@ModelAttribute("userDTO") UserDTO userDTO,
//                               HttpServletRequest request,
//                               HttpServletResponse response,
//                               RedirectAttributes redirectAttributes) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            log.info("User {} logged in successfully", userDTO.getUsername());
//            return "redirect:/home";
//        } catch (AuthenticationException e) {
//            log.error("Login failed for user {}", userDTO.getUsername(), e);
//            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
//            return "redirect:/login";
//        }
//    }

    @GetMapping("/home")
    public String home(Authentication authentication) {

        String authorities = authentication.getAuthorities()
                .stream()
                .findFirst()
                .orElseThrow(() -> new SecurityException("Something went terribly wrong with security. There is no role assigned to the user"))
                .toString();
        log.info("Current user role: " + authorities);

        return switch (authorities) {
            case "CLIENT" -> "client_homepage";
            case "RESTAURANT_OWNER" -> "restaurantOwner_homepage";
            default ->
                    throw new SecurityException("Something went terribly wrong with security. No valid role assigned to the current user");
        };
    }

    //@GetMapping("/home")
//public String home(@AuthenticationPrincipal UserDetails userDetails) {
//    if (userDetails == null) {
//        throw new SecurityException("Użytkownik nie jest zalogowany");
//    }
//
//    String authorities = userDetails.getAuthorities()
//            .stream()
//            .findFirst()
//            .orElseThrow(() -> new SecurityException("Brak przypisanej roli użytkownika"))
//            .getAuthority();
//    log.info("Rola bieżącego użytkownika: " + authorities);
//
//    return switch (authorities) {
//        case "CLIENT" -> "client_homepage";
//        case "RESTAURANT_OWNER" -> "restaurantOwner_homepage";
//        default -> throw new SecurityException("Brak prawidłowej roli przypisanej bieżącemu użytkownikowi");
//    };
//}
    @GetMapping("/register")
    public String showLoginPageWithRegistration(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute("userDTO") UserDTO userDTO,
            RedirectAttributes redirectAttributes) {

        User user = userMapper.map(userDTO);
        User createdUser = userService.registerNewUser(user);

        if (Objects.nonNull(createdUser)) {
            return "redirect:/login";
        }

        return "/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";
    }


}
