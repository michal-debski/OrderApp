package pl.example.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.business.dao.RestaurantDAO;
import pl.example.domain.Address;
import pl.example.domain.Restaurant;
import pl.example.domain.RestaurantOwner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static pl.example.util.DomainInput.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {


    @Mock
    private RestaurantDAO restaurantDAO;

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    void shouldFindAll() {

        when(restaurantDAO.findAll()).thenReturn(List.of(kindOfRestaurant(), kindOfRestaurant1()));

        List<Restaurant> result = restaurantService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void shouldFindRestaurantById() {
        Restaurant restaurant = kindOfRestaurant();

        when(restaurantDAO.findById(4)).thenReturn(Optional.of(restaurant));

        Restaurant result = restaurantService.findRestaurantById(4);

        Assertions.assertThat(restaurant).isEqualTo(result);
    }

    @Test
    void shouldSaveRestaurant() {
        RestaurantOwner restaurantOwner = kindOfRestaurantOwner();
        Address address = kindOfRestaurant().getAddress();
        Restaurant restaurant = kindOfRestaurant();

        when(restaurantDAO.saveRestaurant(restaurant, restaurantOwner, address)).thenReturn(restaurant);

        Restaurant savedRestaurant = restaurantService.saveRestaurant(restaurant, restaurantOwner, address);

        Assertions.assertThat(savedRestaurant).isEqualTo(restaurant);
        Mockito.verify(restaurantDAO, times(1)).saveRestaurant(restaurant, restaurantOwner, address);

    }

    @Test
    void shouldDeleteRestaurant() {
        Integer restaurantId = 1;

        restaurantService.deleteRestaurant(1);
        Mockito.verify(restaurantDAO).deleteRestaurant(restaurantId);

    }

    @Test
    void shouldFindByRestaurantOwnerId() {

        Restaurant restaurant = kindOfRestaurant();
        Restaurant secondRestaurant = kindOfRestaurant1();
        RestaurantOwner restaurantOwner = kindOfRestaurantOwner();

        when(restaurantDAO.findByRestaurantOwnerId(restaurantOwner.getRestaurantOwnerId())).thenReturn(List.of(restaurant, secondRestaurant));

        List<Restaurant> result = restaurantService.findByRestaurantOwnerId(restaurantOwner.getRestaurantOwnerId());

        assertEquals(2, result.size());

    }

    @Test
    void shouldFindAllByStreetName() {
        String streetName = "Testowa";

        when(restaurantDAO.findAllByStreetName(streetName)).thenReturn(List.of(kindOfRestaurant(), kindOfRestaurant1()));
        List<Restaurant> allByStreetName = restaurantService.findAllByStreetName(streetName);

        assertEquals(2, allByStreetName.size());
        Mockito.verify(restaurantDAO).findAllByStreetName(streetName);

    }
}