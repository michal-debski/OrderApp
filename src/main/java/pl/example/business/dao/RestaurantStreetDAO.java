package pl.example.business.dao;

import pl.example.domain.RestaurantStreet;

import java.util.List;

public interface RestaurantStreetDAO {

    List<RestaurantStreet> findAll();

    List<RestaurantStreet> findAllByRestaurantId(Integer id);

    void save(RestaurantStreet restaurantStreet);

    void deleteById(Integer streetId);
}
