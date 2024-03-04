package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.RestaurantOwnerDAO;
import pl.example.domain.RestaurantOwner;

@Service
@AllArgsConstructor
public class RestaurantOwnerService {

    private final RestaurantOwnerDAO restaurantOwnerDAO;

    @Transactional
    public RestaurantOwner findById(Integer restaurantOwnerId) {

        return restaurantOwnerDAO.findById(restaurantOwnerId);
    }
}
