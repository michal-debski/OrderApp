package pl.example.business.dao;

import pl.example.domain.User;

public interface RestaurantOwnerDAO {
    User findRestaurantOwnerByEmail(String email);

    User saveRestaurantOwner(User restaurantOwner);

    User findLoggedRestaurantOwner();
}
