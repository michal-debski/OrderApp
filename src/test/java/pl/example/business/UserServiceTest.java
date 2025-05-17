package pl.example.business;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
//    @InjectMocks
//    private UserService userService;
//
//    @Mock
//    private UserDAO userDAO;
//
//    @Mock
//    private RestaurantOwnerService restaurantOwnerService;
//
//    @Mock
//    private ClientService clientService;
//
//    @Test
//    void shouldRegisterNewUserAsRestaurantOwner() {
//
//        User user = kindOfUser().withRole("RESTAURANT_OWNER");
//        RestaurantOwner restaurantOwner = RestaurantOwner.builder()
//                .name(user.getName())
//                .surname(user.getSurname())
//                .phone(user.getPhone())
//                .email(user.getEmail())
//                .build();
//
//        when(userDAO.registerNewUser(any(User.class))).thenReturn(user);
//
//        userService.registerNewUser(user);
//
//        verify(restaurantOwnerService).saveRestaurantOwner(restaurantOwner);
//
//    }
//
//    @Test
//    void shouldRegisterNewUserAsClient() {
//
//        User user = kindOfUser().withRole("CLIENT");
//        Client restaurantOwner = Client.builder()
//                .name(user.getName())
//                .surname(user.getSurname())
//                .phone(user.getPhone())
//                .email(user.getEmail())
//                .build();
//
//        when(userDAO.registerNewUser(any(User.class))).thenReturn(user);
//
//        userService.registerNewUser(user);
//
//        verify(clientService).saveClient(restaurantOwner);
//    }
}