package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.example.infrastructure.database.entity.StreetEntity;

@Repository
public interface StreetJpaRepository extends JpaRepository<StreetEntity, Integer> {
}
