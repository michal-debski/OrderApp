package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.example.infrastructure.database.entity.CartEntity;
@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity, Integer> {
}
