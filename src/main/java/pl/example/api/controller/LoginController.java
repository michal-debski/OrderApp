package pl.example.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.example.infrastructure.database.repository.ClientRepository;
import pl.example.infrastructure.database.repository.RestaurantOwnerRepository;


@Controller
@Slf4j
@AllArgsConstructor
public class LoginController {



    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


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
            case "RESTAURANT_OWNER" -> "restaurant";
            default ->
                    throw new SecurityException("Something went terribly wrong with security. No valid role assigned to the current user");
        };
    }

}
