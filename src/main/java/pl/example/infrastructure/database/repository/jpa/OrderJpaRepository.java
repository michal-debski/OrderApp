package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.example.infrastructure.database.entity.OrderEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Integer> {

    Optional<OrderEntity> findByOrderNumber(@Param("orderNumber") String orderNumber);

    List<OrderEntity> findAll();

    @Query("""
            SELECT ord FROM OrderEntity ord
            INNER JOIN FETCH ord.user cli
            WHERE cli.email = :email
            """)
    List<OrderEntity> findByClientEmail(@Param("email") String email);

    @Query(
            """
                    SELECT SUM(oi.quantity * m.price) FROM OrderItemEntity oi
                    JOIN oi.meal m
                    JOIN oi.order o
                    WHERE o.orderId = :id
                    """
    )
    BigDecimal getTotalOrderPrice(@Param("id") Integer id);
}
