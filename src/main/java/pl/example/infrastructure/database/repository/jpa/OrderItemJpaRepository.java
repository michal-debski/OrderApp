package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.example.infrastructure.database.entity.OrderItemEntity;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity,Integer> {
}
