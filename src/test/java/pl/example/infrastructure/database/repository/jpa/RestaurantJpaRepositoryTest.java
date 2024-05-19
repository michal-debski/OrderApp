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
import pl.example.util.EntityInput;
import pl.example.util.integration.configuration.PersistenceContainerTestConfiguration;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
class RestaurantJpaRepositoryTest {


    private RestaurantJpaRepository restaurantJpaRepository;


    @Test
    void shouldSaveAndFindByRestaurantOwnerId() {
        RestaurantEntity restaurantEntity = EntityInput.kindOfRestaurantEntity();

        restaurantJpaRepository.save(restaurantEntity);
        Set<RestaurantEntity> byRestaurantOwnerId = restaurantJpaRepository.findByRestaurantOwnerId(
                restaurantEntity.getRestaurantOwner().getRestaurantOwnerId()
        );
        //then
        Assertions.assertEquals(byRestaurantOwnerId.size(), 1);
    }

    @Test
    void shouldSaveAndFindByRestaurantName() {
        //given
        RestaurantEntity restaurantEntity = EntityInput.kindOfRestaurantEntity();
        //when
        restaurantJpaRepository.save(restaurantEntity);
        Optional<RestaurantEntity> test1 = restaurantJpaRepository.findByRestaurantName("test1");
        //then
        assertThat(test1).isPresent();
        assertThat(test1.get().getRestaurantName()).isEqualTo("test1");
    }

}