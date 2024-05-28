package pl.example.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.business.dao.UserDAO;
import pl.example.domain.Client;
import pl.example.domain.RestaurantOwner;
import pl.example.domain.User;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.example.util.DomainInput.kindOfUser;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserDAO userDAO;

    @Mock
    private RestaurantOwnerService restaurantOwnerService;

    @Mock
    private ClientService clientService;

    @Test
    void shouldRegisterNewUserAsRestaurantOwner() {

        User user = kindOfUser().withRole("RESTAURANT_OWNER");
        RestaurantOwner restaurantOwner = RestaurantOwner.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();

        when(userDAO.registerNewUser(any(User.class))).thenReturn(user);

        userService.registerNewUser(user);

        verify(restaurantOwnerService).saveRestaurantOwner(restaurantOwner);

    }

    @Test
    void shouldRegisterNewUserAsClient() {

        User user = kindOfUser().withRole("CLIENT");
        Client restaurantOwner = Client.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();

        when(userDAO.registerNewUser(any(User.class))).thenReturn(user);

        userService.registerNewUser(user);

        verify(clientService).saveClient(restaurantOwner);
    }
}