package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.api.controller.exception.NotFoundException;
import pl.example.business.dao.RestaurantDAO;
import pl.example.domain.Address;
import pl.example.domain.Restaurant;
import pl.example.domain.RestaurantOwner;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor

public class RestaurantService {

    private final RestaurantDAO restaurantDAO;


    public List<Restaurant> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantDAO.findAll();
        log.info("Available restaurants: [{}]", restaurants.size());
        return restaurants;
    }

    @Transactional
    public Restaurant findRestaurantById(Integer id) {
        Optional<Restaurant> restaurant = restaurantDAO.findById(id);
        if (restaurant.isEmpty()) {
            throw new NotFoundException("Could not find restaurant by name: [%s]".formatted(id));
        }
        return restaurant.get();
    }

    @Transactional
    public Restaurant saveRestaurant(Restaurant restaurant, RestaurantOwner restaurantOwner, Address address) {
        log.info("Trying to save Restaurant{}:", restaurant);
        return restaurantDAO.saveRestaurant(restaurant, restaurantOwner, address);
    }

    public void deleteRestaurant(Integer restaurantId) {
        log.info("Trying to delete Restaurant{}:", restaurantId);
        restaurantDAO.deleteRestaurant(restaurantId);
    }


    public List<Restaurant> findByRestaurantOwnerId(Integer id) {
        log.info("Trying to find Restaurant{}:", id);
        return restaurantDAO.findByRestaurantOwnerId(id);
    }


    public List<Restaurant> findAllByStreetName(String street) {
        log.info("Trying to find all Restaurants by street{}:", street);
        return restaurantDAO.findAllByStreetName(street);
    }
}

