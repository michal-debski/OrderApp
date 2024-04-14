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

    private final ClientRepository clientRepository;
    private final RestaurantOwnerRepository restaurantOwnerRepository;

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

//
//    @PostMapping("/login")
//    public String loginPage(HttpServletRequest request) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            Object principal = auth.getPrincipal();
//            if (principal instanceof CustomUserDetails) {
//                String username = ((CustomUserDetails) principal).getUsername();
//                if (request.isUserInRole("CLIENT")) {
//                    Client client = clientRepository.findByEmail(username).get();
//                    Integer clientId = client.getClientId();
//                    return "redirect:/client/" + clientId;
//                } else if (request.isUserInRole("RESTAURANT_OWNER")) {
//                    RestaurantOwner restaurantOwner = restaurantOwnerRepository.findByEmail(username).get();
//                    Integer restaurantOwnerId = restaurantOwner.getRestaurantOwnerId();
//
//                    return "redirect:/restaurantOwner/" + restaurantOwnerId;
//                }
//            }
//        }
//        // Domyślnie przekieruj na stronę główną
//        return "redirect:/";
//    }
}
