package pl.example.infrastructure.database.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RestaurantOwnerRepositoryTest {
//    @Mock
//    private RestaurantOwnerJpaRepository restaurantOwnerJpaRepository;
//    @Mock
//    private RestaurantOwnerEntityMapper restaurantOwnerEntityMapper;
//    @Mock
//    private UserJpaRepository userJpaRepository;
//    @Mock
//    private Authentication authentication;
//
//    @Mock
//    private SecurityContext securityContext;
//    @InjectMocks
//    private RestaurantOwnerRepository restaurantOwnerRepository;
//
//    private RestaurantOwnerEntity restaurantOwnerEntity;
//    private RestaurantOwner restaurantOwner;
//    private UserEntity userEntity;
//
//    @BeforeEach
//    public void setUp() {
//        restaurantOwnerEntity = EntityInput.kindOfRestaurantOwnerEntity();
//        restaurantOwner = DomainInput.kindOfRestaurantOwner();
//        userEntity = EntityInput.kindOfUserEntity();
//    }
//
//    @Test
//    void shouldFindRestaurantOwnerById() {
//        when(restaurantOwnerJpaRepository.findById(1)).thenReturn(Optional.of(restaurantOwnerEntity));
//        when(restaurantOwnerEntityMapper.mapFromEntity(restaurantOwnerEntity)).thenReturn(restaurantOwner);
//
//        RestaurantOwner restaurantOwnerFound = restaurantOwnerRepository.findRestaurantOwnerById(1);
//        Assertions.assertEquals(restaurantOwner, restaurantOwnerFound);
//    }
//
//    @Test
//    void shouldSaveRestaurantOwner() {
//        when(restaurantOwnerEntityMapper.mapToEntity(restaurantOwner)).thenReturn(restaurantOwnerEntity);
//        when(restaurantOwnerJpaRepository.save(restaurantOwnerEntity)).thenReturn(restaurantOwnerEntity);
//        when(restaurantOwnerEntityMapper.mapFromEntity(restaurantOwnerEntity)).thenReturn(restaurantOwner);
//
//        RestaurantOwner savedRestaurantOwner = restaurantOwnerRepository.saveRestaurantOwner(restaurantOwner);
//
//        assertEquals(restaurantOwner, savedRestaurantOwner);
//
//        verify(restaurantOwnerEntityMapper, times(1)).mapToEntity(restaurantOwner);
//        verify(restaurantOwnerJpaRepository, times(1)).save(restaurantOwnerEntity);
//        verify(restaurantOwnerEntityMapper, times(1)).mapFromEntity(restaurantOwnerEntity);
//
//    }
//
//    @Test
//    void shouldFindLoggedRestaurantOwner() {
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//
//        when(authentication.getName()).thenReturn(userEntity.getUsername());
//        when(userJpaRepository.findByUsername(userEntity.getUsername())).thenReturn(userEntity);
//        when(restaurantOwnerJpaRepository.findByEmail(userEntity.getEmail())).thenReturn(Optional.of(restaurantOwnerEntity));
//        when(restaurantOwnerEntityMapper.mapFromEntity(restaurantOwnerEntity)).thenReturn(restaurantOwner);
//
//        RestaurantOwner result = restaurantOwnerRepository.findLoggedRestaurantOwner();
//
//        Assertions.assertEquals(restaurantOwner, result);
//
//        verify(securityContext).getAuthentication();
//        verify(authentication).getName();
//        verify(userJpaRepository).findByUsername(userEntity.getUsername());
//        verify(restaurantOwnerJpaRepository).findByEmail(userEntity.getEmail());
//        verify(restaurantOwnerEntityMapper).mapFromEntity(restaurantOwnerEntity);
//    }
//
//    @Test
//    void shouldNotFindLoggedRestaurantOwner_UserNotFound() {
//
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//
//        when(authentication.getName()).thenReturn(userEntity.getUsername());
//        when(userJpaRepository.findByUsername(userEntity.getUsername())).thenReturn(userEntity);
//        when(restaurantOwnerJpaRepository.findByEmail(userEntity.getEmail())).thenReturn(Optional.empty());
//
//        Assertions.assertThrows(SecurityException.class, () -> restaurantOwnerRepository.findLoggedRestaurantOwner());
//
//
//        verify(securityContext).getAuthentication();
//        verify(authentication).getName();
//        verify(userJpaRepository).findByUsername(userEntity.getUsername());
//        verify(restaurantOwnerJpaRepository).findByEmail(userEntity.getEmail());
//    }

}