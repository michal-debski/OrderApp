package pl.example.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import pl.example.util.integration.configuration.PersistenceContainerTestConfiguration;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
class OrderItemJpaRepositoryTest {
    private OrderItemJpaRepository orderItemJpaRepository;

    @Transactional
    @Test
    void shouldDeleteOrderItemsByOrderId() {

    }
}