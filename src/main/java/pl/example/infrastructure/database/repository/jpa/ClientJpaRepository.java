package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.example.infrastructure.database.entity.ClientEntity;

import java.util.Optional;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, Integer> {

    Optional<ClientEntity> findByEmail(String email);
}
