package pl.example.business.dao;

import pl.example.domain.RestaurantOwner;

public interface RestaurantOwnerDAO {
    RestaurantOwner findRestaurantOwnerById(Integer id);

    RestaurantOwner saveRestaurantOwner(RestaurantOwner restaurantOwner);

    RestaurantOwner findLoggedRestaurantOwner();
}
