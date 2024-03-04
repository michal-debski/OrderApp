package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.example.infrastructure.database.repository.ClientRepository;
import pl.example.infrastructure.database.repository.RestaurantOwnerRepository;

@RequestMapping
@Controller
@AllArgsConstructor
public class LoginController {

    private final ClientRepository clientRepository;
    private final RestaurantOwnerRepository restaurantOwnerRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
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
