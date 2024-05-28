package pl.example.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.business.dao.RestaurantStreetDAO;
import pl.example.domain.Restaurant;
import pl.example.domain.RestaurantStreet;
import pl.example.domain.Street;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static pl.example.util.DomainInput.*;

@ExtendWith(MockitoExtension.class)
class RestaurantStreetServiceTest {

    @Mock
    private RestaurantStreetDAO restaurantStreetDAO;

    @Mock
    private StreetService streetService;

    @InjectMocks
    private RestaurantStreetService restaurantStreetService;

    @Test
    void shouldFindAll() {

        List<RestaurantStreet> restaurantStreetList = List.of(kindOfRestaurantStreet(), kindOfRestaurantStreet1());
        when(restaurantStreetDAO.findAll()).thenReturn(restaurantStreetList);

        List<RestaurantStreet> result = restaurantStreetService.findAll();

        Assertions.assertThat(result).isNotEmpty();
        assertEquals(2,result.size());

    }

    @Test
    void shouldSave() {

        Restaurant restaurant = kindOfRestaurant();
        Street street = kindOfStreet().withStreetId(1);
        Street secondStreet = kindOfStreet1().withStreetId(2);
        List<String> restaurantStreets =
                List.of(String.valueOf(street.getStreetId()), String.valueOf(secondStreet.getStreetId()));

        when(streetService.findById(1)).thenReturn(street);
        when(streetService.findById(2)).thenReturn(secondStreet);

        restaurantStreetService.save(restaurant,restaurantStreets);

        Mockito.verify(streetService, times(1)).findById(1);
        Mockito.verify(streetService, times(1)).findById(2);
        Mockito.verify(restaurantStreetDAO, times(2)).save(any(RestaurantStreet.class));
    }
}