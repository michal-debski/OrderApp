package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.RestaurantDAO;
import pl.example.domain.Address;
import pl.example.domain.Restaurant;
import pl.example.domain.User;
import pl.example.infrastructure.database.entity.AddressEntity;
import pl.example.infrastructure.database.entity.RestaurantEntity;
import pl.example.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.example.infrastructure.database.repository.mapper.AddressEntityMapper;
import pl.example.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import pl.example.infrastructure.database.repository.mapper.UserEntityMapper;
import pl.example.infrastructure.security.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RestaurantRepository implements RestaurantDAO {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final UserEntityMapper userEntityMapper;
    private final AddressEntityMapper addressEntityMapper;

    @Override
    public List<Restaurant> findByRestaurantOwnerEmail(String email) {
        return restaurantJpaRepository.findByUserEmail(email).stream()
                .map(restaurantEntityMapper::mapFromEntity).toList();
    }


    @Override
    public Optional<Restaurant> findRestaurantById(Integer id) {
        return restaurantJpaRepository.findById(id)
                .map(restaurantEntityMapper::mapFromEntity);
    }

    @Override
    public List<Restaurant> findAllRestaurants() {
        return restaurantJpaRepository.findAll().stream()
                .map(restaurantEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant, User restaurantOwner, Address address) {

        AddressEntity addressEntity = addressEntityMapper.mapToEntity(address);
        RestaurantEntity restaurantEntity = restaurantEntityMapper.mapToEntity(restaurant);
        UserEntity restaurantOwnerEntity = userEntityMapper.mapToEntity(restaurantOwner);
        restaurantEntity.setUser(restaurantOwnerEntity);
        restaurantEntity.setAddress(addressEntity);
        RestaurantEntity restaurantSaved = restaurantJpaRepository.save(restaurantEntity);
        return restaurantEntityMapper.mapFromEntity(restaurantSaved);
    }

    @Override
    public void deleteRestaurant(Integer restaurantId) {
        restaurantJpaRepository.delete(restaurantJpaRepository.findById(restaurantId).get());
    }

    @Override
    public List<Restaurant> findAllRestaurantByStreetName(String street) {
        return restaurantJpaRepository.findAllByStreetName(street)
                .stream().map(restaurantEntityMapper::mapFromEntity)
                .toList();
    }


}
