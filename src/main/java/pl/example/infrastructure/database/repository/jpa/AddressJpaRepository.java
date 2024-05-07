package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.example.infrastructure.database.entity.AddressEntity;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Integer> {
}
