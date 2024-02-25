package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.RestaurantDAO;
import pl.example.domain.Restaurant;
import pl.example.infrastructure.database.entity.RestaurantEntity;
import pl.example.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.example.infrastructure.database.repository.mapper.RestaurantEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RestaurantRepository implements RestaurantDAO {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    @Override
    public Optional<Restaurant> findByName(String name) {
        return restaurantJpaRepository.findByName(name)
                .map(restaurantEntityMapper::mapFromEntity);
    }


    @Override
    public List<Restaurant> findAll() {
        return restaurantJpaRepository.findAll().stream()
                .map(restaurantEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        RestaurantEntity toSave = restaurantEntityMapper.mapToEntity(restaurant);
        RestaurantEntity saved = restaurantJpaRepository.save(toSave);
        return restaurantEntityMapper.mapFromEntity(saved);
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) {
        RestaurantEntity toRemove = restaurantEntityMapper.mapToEntity(restaurant);
        restaurantJpaRepository.delete(toRemove);
    }

//    @Override
//    public List<Restaurant> findByStreet(String street) {
//        return restaurantJpaRepository.findByStreet(street).stream()
//                .map(restaurantEntityMapper::mapFromEntity).toList();
//    }


}
