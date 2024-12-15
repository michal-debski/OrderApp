package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.example.infrastructure.database.entity.RestaurantEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity,Integer> {

    @Query("""
            SELECT rest FROM RestaurantEntity rest
            INNER JOIN FETCH rest.restaurantOwner own
            WHERE own.restaurantOwnerId=:id
            """)
    Set<RestaurantEntity> findByRestaurantOwnerId(final @Param("id") Integer id);
    Optional<RestaurantEntity> findByRestaurantName(String name);

    @Query("""
        SELECT rs.restaurant FROM RestaurantStreetEntity rs
        JOIN rs.restaurant r
        JOIN rs.street s
        WHERE LOWER(s.name) LIKE LOWER(CONCAT('%',:street,'%'))
        """)
    List<RestaurantEntity> findAllByStreetName(final @Param("street") String street);
}
