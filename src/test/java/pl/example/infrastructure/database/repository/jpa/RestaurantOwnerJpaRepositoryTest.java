package pl.example.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.example.infrastructure.database.entity.RestaurantOwnerEntity;
import pl.example.util.EntityInput;
import pl.example.util.integration.configuration.PersistenceContainerTestConfiguration;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
class RestaurantOwnerJpaRepositoryTest {


    private RestaurantOwnerJpaRepository restaurantOwnerJpaRepository;

    @Test
    void shouldSaveAndFindById() {

        //given
        RestaurantOwnerEntity restaurantOwner = EntityInput.kindOfRestaurantOwnerEntity();

        //when
        restaurantOwnerJpaRepository.save(restaurantOwner);
        Optional<RestaurantOwnerEntity> restaurantOwnerEntity = restaurantOwnerJpaRepository.findById(restaurantOwner.getRestaurantOwnerId());
        //then
        assertThat(restaurantOwnerEntity).isPresent();
        assertEquals(restaurantOwner, restaurantOwnerEntity.get());


    }

    @Test
    void shouldSaveAndFindByEmail() {
        //given
        RestaurantOwnerEntity restaurantOwner = EntityInput.kindOfRestaurantOwnerEntity();
        //when
        restaurantOwnerJpaRepository.save(restaurantOwner);
        Optional<RestaurantOwnerEntity> restaurantOwnerEntity = restaurantOwnerJpaRepository.findByEmail(restaurantOwner.getEmail());
        //then
        assertThat(restaurantOwnerEntity).isPresent();
        assertEquals(restaurantOwner, restaurantOwnerEntity.get());
    }
}