package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.example.domain.Restaurant;
import pl.example.infrastructure.database.entity.RestaurantEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity,Integer> {

    @Query("""
            SELECT rest FROM RestaurantEntity rest
            LEFT JOIN FETCH rest.restaurantOwner own
            WHERE own.id=:id
            """)
    Set<RestaurantEntity> findByRestaurantOwnerId(final @Param("id") Integer id);
    Optional<RestaurantEntity> findByName(String name);
//    List<RestaurantEntity> findByStreet(String street);


}
