package pl.example.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.example.infrastructure.database.entity.OrderEntity;
import pl.example.infrastructure.database.entity.RestaurantEntity;
import pl.example.util.EntityInput;
import pl.example.util.integration.configuration.PersistenceContainerTestConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.example.util.EntityInput.kindOfRestaurantEntity;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class OrderJpaRepositoryTest {

    private OrderJpaRepository orderJpaRepository;
    private RestaurantJpaRepository restaurantJpaRepository;

    @Test
    void shouldSaveAndFindByClientId() {
        OrderEntity orderEntity = EntityInput.kindOfOrderEntity();

        // Tworzenie i zapisanie restauracji
        RestaurantEntity restaurant = kindOfRestaurantEntity();
        restaurant.setRestaurantId(4);
        RestaurantEntity save = restaurantJpaRepository.save(restaurant);

        // Ustawienie restauracji dla zamówienia
        orderEntity.setRestaurant(save);

        // Zapisanie zamówienia
        orderJpaRepository.save(orderEntity);

        // Pobranie zamówień dla klienta
        List<OrderEntity> ordersByClientId = orderJpaRepository.findByClientId(orderEntity.getClient().getClientId());

        // Sprawdzenie czy znaleziono jedno zamówienie
        assertEquals(1, ordersByClientId.size());
    }

}