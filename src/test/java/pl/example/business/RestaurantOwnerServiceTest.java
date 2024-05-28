package pl.example.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.business.dao.RestaurantOwnerDAO;
import pl.example.domain.RestaurantOwner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static pl.example.util.DomainInput.kindOfRestaurantOwner;

@ExtendWith(MockitoExtension.class)
class RestaurantOwnerServiceTest {

    @Mock
    private RestaurantOwnerDAO restaurantOwnerDAO;

    @InjectMocks
    private RestaurantOwnerService restaurantOwnerService;

    @Test
    void shouldFindById() {
        RestaurantOwner restaurantOwner = kindOfRestaurantOwner().withRestaurantOwnerId(1);

        when(restaurantOwnerDAO.findById(1)).thenReturn(restaurantOwner);

        RestaurantOwner result = restaurantOwnerService.findById(restaurantOwner.getRestaurantOwnerId());

        assertEquals(restaurantOwner, result);
    }

    @Test
    void shouldSaveRestaurantOwner() {
        RestaurantOwner restaurantOwner = kindOfRestaurantOwner();
        when(restaurantOwnerDAO.saveRestaurantOwner(restaurantOwner)).thenReturn(restaurantOwner);

        RestaurantOwner savedRestaurantOwner = restaurantOwnerService.saveRestaurantOwner(restaurantOwner);

        assertEquals("test@gmail.com",savedRestaurantOwner.getEmail());
        verify(restaurantOwnerDAO, times(1)).saveRestaurantOwner(restaurantOwner);

    }

    @Test
    void shouldFindLoggedRestaurantOwner() {

        RestaurantOwner restaurantOwner = kindOfRestaurantOwner().withName("Tomasz");
        when(restaurantOwnerDAO.findLoggedRestaurantOwner()).thenReturn(restaurantOwner);

        RestaurantOwner loggedRestaurantOwner = restaurantOwnerService.findLoggedRestaurantOwner();

        Assertions.assertThat(restaurantOwner).isEqualTo(loggedRestaurantOwner);
    }
}