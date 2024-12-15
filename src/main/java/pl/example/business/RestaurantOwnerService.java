package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.RestaurantOwnerDAO;
import pl.example.domain.RestaurantOwner;

@Service
@AllArgsConstructor
@Slf4j
public class RestaurantOwnerService {

    private final RestaurantOwnerDAO restaurantOwnerDAO;


    public RestaurantOwner findRestaurantOwnerById(Integer restaurantOwnerId) {
        log.info("Trying to find Restaurant Owner: {}", restaurantOwnerId);
        return restaurantOwnerDAO.findById(restaurantOwnerId);
    }

    @Transactional
    public RestaurantOwner saveRestaurantOwner(RestaurantOwner restaurantOwner) {
        log.info("Trying to save Restaurant Owner: {}", restaurantOwner);
        return restaurantOwnerDAO.saveRestaurantOwner(restaurantOwner);
    }

    public RestaurantOwner findLoggedRestaurantOwner() {
        return restaurantOwnerDAO.findLoggedRestaurantOwner();
    }
}
