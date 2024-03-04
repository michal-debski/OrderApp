package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.example.infrastructure.database.entity.OrderItemEntity;

@Repository
public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM OrderItemEntity oi WHERE oi.order.id = :orderId")
    void deleteOrderItemsByOrderId(@Param("orderId") Integer orderId);
}
