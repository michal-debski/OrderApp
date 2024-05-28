package pl.example.infrastructure.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
import pl.example.util.DomainInput;
import pl.example.util.EntityInput;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantRepositoryTest {

    @Mock
    private RestaurantJpaRepository restaurantJpaRepository;
    @Mock
    private RestaurantEntityMapper restaurantEntityMapper;
    @Mock
    private AddressEntityMapper addressEntityMapper;
    @Mock
    private RestaurantOwnerEntityMapper restaurantOwnerEntityMapper;

    @InjectMocks
    private RestaurantRepository restaurantRepository;

    private RestaurantEntity restaurantEntity;
    private RestaurantEntity restaurantEntity1;
    private RestaurantEntity restaurantEntity2;
    private Restaurant restaurant;
    private Restaurant restaurant1;
    private Restaurant restaurant2;

    private RestaurantOwnerEntity restaurantOwnerEntity;
    private AddressEntity addressEntity;

    private RestaurantOwner restaurantOwner;
    private Address address;

    @BeforeEach
    public void setUp() {
        restaurantEntity = EntityInput.kindOfRestaurantEntity();
        restaurantEntity1 = EntityInput.kindOfRestaurantEntity1();
        restaurantEntity2 = EntityInput.kindOfRestaurantEntity2();
        restaurantOwnerEntity = EntityInput.kindOfRestaurantOwnerEntity();
        addressEntity = EntityInput.kindOfRestaurantEntity().getAddress();
        restaurant = DomainInput.kindOfRestaurant();
        restaurant1 = DomainInput.kindOfRestaurant1();
        restaurant2 = DomainInput.kindOfRestaurant2();
        restaurantOwner = DomainInput.kindOfRestaurantOwner();
        address = DomainInput.kindOfRestaurant().getAddress();
    }

    @Test
    void shouldFindByRestaurantOwnerId() {
        when(restaurantJpaRepository.findByRestaurantOwnerId(3)).thenReturn(Set.of(restaurantEntity, restaurantEntity2));
        when(restaurantEntityMapper.mapFromEntity(restaurantEntity)).thenReturn(restaurant);
        when(restaurantEntityMapper.mapFromEntity(restaurantEntity2)).thenReturn(restaurant2);

        List<Restaurant> restaurantsByRestaurantOwnerId = restaurantRepository.findByRestaurantOwnerId(3);


        assertEquals(2, restaurantsByRestaurantOwnerId.size());
    }


    @Test
    void shouldFindById() {
        when(restaurantJpaRepository.findById(5)).thenReturn(Optional.ofNullable(restaurantEntity1));
        when(restaurantEntityMapper.mapFromEntity(restaurantEntity1)).thenReturn(restaurant1);

        Optional<Restaurant> result = restaurantRepository.findById(5);

        Assertions.assertThat(result).isPresent();
        assertEquals(restaurant1, result.get());
    }

    @Test
    void shouldFindAll() {
        when(restaurantJpaRepository.findAll()).thenReturn(List.of(restaurantEntity, restaurantEntity1, restaurantEntity2));
        when(restaurantEntityMapper.mapFromEntity(restaurantEntity)).thenReturn(restaurant);
        when(restaurantEntityMapper.mapFromEntity(restaurantEntity1)).thenReturn(restaurant1);
        when(restaurantEntityMapper.mapFromEntity(restaurantEntity2)).thenReturn(restaurant2);

        List<Restaurant> allRestaurants = restaurantRepository.findAll();

        assertNotNull(allRestaurants);
        assertEquals(3, allRestaurants.size());
    }

    @Test
    void shouldSaveRestaurant() {

        when(restaurantEntityMapper.mapToEntity(restaurant)).thenReturn(restaurantEntity);
        when(restaurantOwnerEntityMapper.mapToEntity(restaurantOwner)).thenReturn(restaurantOwnerEntity);
        when(addressEntityMapper.mapToEntity(address)).thenReturn(addressEntity);
        when(restaurantJpaRepository.save(restaurantEntity)).thenReturn(restaurantEntity);
        when(restaurantEntityMapper.mapFromEntity(restaurantEntity)).thenReturn(restaurant);


        Restaurant result = restaurantRepository.saveRestaurant(restaurant, restaurantOwner, address);

        assertEquals(restaurant, result);

    }

    @Test
    void shouldDeleteRestaurant() {

        when(restaurantJpaRepository.findById(4)).thenReturn(Optional.of(restaurantEntity));

        restaurantRepository.deleteRestaurant(4);

        when(restaurantJpaRepository.findById(4)).thenReturn(Optional.empty());

        assertFalse(restaurantJpaRepository.findById(4).isPresent());

    }

    @Test
    void shouldFindAllByStreetName() {

        when(restaurantJpaRepository.findAllByStreetName("Testowa")).thenReturn(List.of(restaurantEntity));

        List<Restaurant> allByStreetName = restaurantRepository.findAllByStreetName("Testowa");

        assertEquals(1, allByStreetName.size());

    }
}