package pl.example.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.example.infrastructure.database.entity.RestaurantEntity;
import pl.example.infrastructure.database.entity.RestaurantStreetEntity;
import pl.example.util.integration.configuration.PersistenceContainerTestConfiguration;

import java.util.List;

import static pl.example.util.EntityInput.kindOfRestaurantEntity;
import static pl.example.util.EntityInput.kindOfRestaurantStreetEntity;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RestaurantStreetJpaRepositoryTest {


    private RestaurantStreetJpaRepository restaurantStreetJpaRepository;
    private RestaurantJpaRepository restaurantJpaRepository;

    @Test
    void shouldSaveAndFindAllByRestaurantId() {
        //given
        RestaurantStreetEntity restaurantStreetEntity = kindOfRestaurantStreetEntity();
        RestaurantEntity restaurant = kindOfRestaurantEntity();
        //when
        RestaurantEntity saved = restaurantJpaRepository.save(restaurant);
        restaurantStreetEntity.setRestaurant(saved);
        restaurantStreetJpaRepository.save(restaurantStreetEntity);
        List<RestaurantStreetEntity> allByRestaurantId = restaurantStreetJpaRepository.findAllByRestaurantId(
                restaurantStreetEntity.getRestaurant().getRestaurantId()
        );
        //then
        Assertions.assertEquals(1, allByRestaurantId.size());

    }
}