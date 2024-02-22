package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.example.infrastructure.database.entity.ClientEntity;

public interface ClientJpaRepository extends JpaRepository<ClientEntity,Integer> {
}
