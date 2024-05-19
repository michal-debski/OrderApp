package pl.example.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.example.infrastructure.database.entity.StreetEntity;
import pl.example.util.integration.configuration.PersistenceContainerTestConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)

class StreetJpaRepositoryTest {

    private StreetJpaRepository streetJpaRepository;
    @Test
    void shouldFindAllCorrectly() {

        List<StreetEntity> allStreets = streetJpaRepository.findAll();


        assertEquals(400,allStreets.size());
    }
}