package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.example.infrastructure.database.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Integer> {

    Optional<OrderEntity> findByOrderNumber(String orderNumber);

    List<OrderEntity> findAll();

    @Query("""
            SELECT ord FROM OrderEntity ord 
            INNER JOIN FETCH ord.client cli
            WHERE cli.id = :id
            """)
    List<OrderEntity> findByClientId(@Param("id") Integer id);
}
