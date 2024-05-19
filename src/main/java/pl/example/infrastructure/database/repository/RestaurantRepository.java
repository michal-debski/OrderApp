package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.RestaurantDAO;
import pl.example.domain.Address;
import pl.example.domain.Restaurant;
import pl.example.domain.RestaurantOwner;
import pl.example.infrastructure.database.entity.AddressEntity;
import pl.example.infrastructure.database.entity.RestaurantEntity;
import pl.example.infrastructure.database.entity.RestaurantOwnerEntity;
import pl.example.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.example.infrastructure.database.repository.mapper.AddressEntityMapper;
import pl.example.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import pl.example.infrastructure.database.repository.mapper.RestaurantOwnerEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RestaurantRepository implements RestaurantDAO {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    private final AddressEntityMapper addressEntityMapper;
    private final RestaurantOwnerEntityMapper restaurantOwnerEntityMapper;

    @Override
    public List<Restaurant> findByRestaurantOwnerId(Integer id) {
        return restaurantJpaRepository.findByRestaurantOwnerId(id).stream()
                .map(restaurantEntityMapper::mapFromEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<Restaurant> findByName(String name) {
        return restaurantJpaRepository.findByRestaurantName(name)
                .map(restaurantEntityMapper::mapFromEntity);
    }

    @Override
    public Optional<Restaurant> findById(Integer id) {
        return restaurantJpaRepository.findById(id)
                .map(restaurantEntityMapper::mapFromEntity);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantJpaRepository.findAll().stream()
                .map(restaurantEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant, RestaurantOwner restaurantOwner, Address address) {

        AddressEntity addressEntity = addressEntityMapper.mapToEntity(address);
        RestaurantEntity restaurantEntity = restaurantEntityMapper.mapToEntity(restaurant);
        RestaurantOwnerEntity restaurantOwnerEntity = restaurantOwnerEntityMapper.mapToEntity(restaurantOwner);
        restaurantEntity.setRestaurantOwner(restaurantOwnerEntity);
        restaurantEntity.setAddress(addressEntity);
        RestaurantEntity restaurantSaved = restaurantJpaRepository.save(restaurantEntity);
        return restaurantEntityMapper.mapFromEntity(restaurantSaved);
    }

    @Override
    public void deleteRestaurant(Integer restaurantId) {
        restaurantJpaRepository.delete(restaurantJpaRepository.findById(restaurantId).get());
    }

    @Override
    public List<Restaurant> findAllByStreetName(String street) {
        return restaurantJpaRepository.findAllByStreetName(street)
                .stream().map(restaurantEntityMapper::mapFromEntity)
                .toList();
    }


}
