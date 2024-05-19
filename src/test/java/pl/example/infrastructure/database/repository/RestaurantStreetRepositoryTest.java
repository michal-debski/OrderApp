package pl.example.infrastructure.database.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.domain.RestaurantStreet;
import pl.example.infrastructure.database.entity.RestaurantStreetEntity;
import pl.example.infrastructure.database.repository.jpa.RestaurantStreetJpaRepository;
import pl.example.infrastructure.database.repository.mapper.RestaurantStreetEntityMapper;
import pl.example.util.DomainInput;
import pl.example.util.EntityInput;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantStreetRepositoryTest {

    @Mock
    private RestaurantStreetJpaRepository restaurantStreetJpaRepository;

    @Mock
    private RestaurantStreetEntityMapper restaurantStreetEntityMapper;

    @InjectMocks
    private RestaurantStreetRepository restaurantStreetRepository;

    private RestaurantStreetEntity restaurantStreetEntity;
    private RestaurantStreet restaurantStreet;
    private RestaurantStreetEntity restaurantStreetEntity1;
    private RestaurantStreet restaurantStreet1;

    @BeforeEach
    public void setUp() {
        restaurantStreetEntity = EntityInput.kindOfRestaurantStreetEntity();
        restaurantStreet = DomainInput.kindOfRestaurantStreet();
        restaurantStreetEntity1 = EntityInput.kindOfRestaurantStreetEntity1();
        restaurantStreet1 = DomainInput.kindOfRestaurantStreet1();
        when(restaurantStreetEntityMapper.mapFromEntity(restaurantStreetEntity)).thenReturn(restaurantStreet);
        when(restaurantStreetEntityMapper.mapFromEntity(restaurantStreetEntity1)).thenReturn(restaurantStreet1);


    }

    @Test
    void shouldFindAll() {

        when(restaurantStreetJpaRepository.findAll()).thenReturn(List.of(restaurantStreetEntity, restaurantStreetEntity1));

        List<RestaurantStreet> all = restaurantStreetRepository.findAll();

        assertNotNull(all);
        assertEquals(2, all.size());

    }

    @Test
    void shouldFindAllByRestaurantId() {
        when(restaurantStreetJpaRepository.findAllByRestaurantId(4)).thenReturn(List.of(restaurantStreetEntity, restaurantStreetEntity1));

        List<RestaurantStreet> all = restaurantStreetRepository.findAllByRestaurantId(4);

        assertNotNull(all);
        assertEquals(2, all.size());
    }

    @Test
    void shouldSave() {
        when(restaurantStreetEntityMapper.mapToEntity(restaurantStreet)).thenReturn(restaurantStreetEntity);
        when(restaurantStreetJpaRepository.save(restaurantStreetEntity)).thenReturn(restaurantStreetEntity);
        when(restaurantStreetEntityMapper.mapFromEntity(restaurantStreetEntity)).thenReturn(restaurantStreet);

        RestaurantStreet restaurantStreetSaved = restaurantStreetRepository.save(restaurantStreet);

        assertEquals(restaurantStreet, restaurantStreetSaved);
    }

}
