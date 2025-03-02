package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.RestaurantOwnerDAO;
import pl.example.domain.User;

@Service
@AllArgsConstructor
@Slf4j
public class RestaurantOwnerService {

    private final RestaurantOwnerDAO restaurantOwnerDAO;


    public User findRestaurantOwnerByEmail(String email) {
        log.info("Trying to find Restaurant Owner: {}", email);
        return restaurantOwnerDAO.findRestaurantOwnerByEmail(email);
    }

    @Transactional
    public User saveRestaurantOwner(User restaurantOwner) {
        log.info("Trying to save Restaurant Owner: {}", restaurantOwner);
        return restaurantOwnerDAO.saveRestaurantOwner(restaurantOwner);
    }

    public User findLoggedRestaurantOwner() {
        return restaurantOwnerDAO.findLoggedRestaurantOwner();
    }
}
