package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.example.infrastructure.database.entity.OrderEntity;

import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Integer> {

    Optional<OrderEntity> findByOrderNumber(String orderNumber);
}
