package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.example.domain.Street;
import pl.example.infrastructure.database.entity.StreetEntity;

import java.util.List;

@Repository
public interface StreetJpaRepository extends JpaRepository<StreetEntity, Integer> {

    @Query("""
            SELECT str FROM StreetEntity str
            Inner JOIN FETCH str.restaurant rest
            WHERE rest.id=:id
            """)
    List<StreetEntity> findAllByRestaurantId(@Param("id") Integer id);
}
