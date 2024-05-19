package pl.example.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.example.infrastructure.database.entity.ClientEntity;
import pl.example.util.EntityInput;
import pl.example.util.integration.configuration.PersistenceContainerTestConfiguration;

import java.util.Optional;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
class ClientJpaRepositoryTest {

    ClientJpaRepository clientJpaRepository;

    @Test
    void shouldSaveAndFindByEmailCorrectly() {

        ClientEntity clientEntity = EntityInput.kindOfClientEntity();
        clientJpaRepository.save(clientEntity);
        Optional<ClientEntity> byEmail = clientJpaRepository.findByEmail(clientEntity.getEmail());
        org.assertj.core.api.Assertions.assertThat(byEmail).isPresent();
        Assertions.assertEquals("jan.testowy@gmail.com", byEmail.get().getEmail());

    }
}