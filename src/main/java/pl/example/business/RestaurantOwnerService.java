package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.business.dao.RestaurantOwnerDAO;
import pl.example.domain.Restaurant;
import pl.example.domain.RestaurantOwner;
@Service
@AllArgsConstructor
public class RestaurantOwnerService {

    private final RestaurantOwnerDAO restaurantOwnerDAO;
    public RestaurantOwner findById(Integer restaurantOwnerId) {

        return restaurantOwnerDAO.findById(restaurantOwnerId);
    }
}
