package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.UserDAO;
import pl.example.domain.Client;
import pl.example.domain.RestaurantOwner;
import pl.example.domain.User;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserDAO userDAO;
    private final RestaurantOwnerService restaurantOwnerService;
    private final ClientService clientService;


    @Transactional
    public User registerNewUser(User user) {
        User created = userDAO.registerNewUser(user);
        log.info("Trying to create new user");
        switch (user.getRole()) {
            case "RESTAURANT_OWNER":
                RestaurantOwner restaurantOwner = RestaurantOwner.builder()
                        .name(user.getName())
                        .surname(user.getSurname())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .build();
                restaurantOwnerService.saveRestaurantOwner(restaurantOwner);
                break;

            case "CLIENT":
                Client client = Client.builder()
                        .name(user.getName())
                        .surname(user.getSurname())
                        .phone(user.getPhone())
                        .email(user.getEmail())
                        .build();
                clientService.saveClient(client);
                break;

            default:
                log.warn("Unknown role: {}", user.getRole());
                break;
        }

        log.info("User created: {}", created);
        return created;
    }

}