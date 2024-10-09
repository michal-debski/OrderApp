package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import pl.example.api.controller.exception.NotFoundException;
import pl.example.business.dao.RestaurantOwnerDAO;
import pl.example.domain.RestaurantOwner;
import pl.example.infrastructure.database.entity.RestaurantOwnerEntity;
import pl.example.infrastructure.database.repository.jpa.RestaurantOwnerJpaRepository;
import pl.example.infrastructure.database.repository.mapper.RestaurantOwnerEntityMapper;
import pl.example.infrastructure.security.UserJpaRepository;

@Repository
@Slf4j
@AllArgsConstructor
public class RestaurantOwnerRepository implements RestaurantOwnerDAO {

    private final RestaurantOwnerJpaRepository restaurantOwnerJpaRepository;
    private final RestaurantOwnerEntityMapper restaurantOwnerEntityMapper;
    private final UserJpaRepository userJpaRepository;

    @Override
    public RestaurantOwner findById(Integer id) {
        return restaurantOwnerJpaRepository.findById(id)
                .map(restaurantOwnerEntityMapper::mapFromEntity).orElseThrow(
                        () -> new NotFoundException("Restaurant Owner not found")
                );

    }

    @Override
    public RestaurantOwner saveRestaurantOwner(RestaurantOwner restaurantOwner) {
        RestaurantOwnerEntity toSave = restaurantOwnerEntityMapper.mapToEntity(restaurantOwner);
        RestaurantOwnerEntity saved = restaurantOwnerJpaRepository.save(toSave);
        log.info("Restaurant Owner was created successfully with email: " + saved.getEmail());
        return restaurantOwnerEntityMapper.mapFromEntity(saved);
    }

    @Override
    public RestaurantOwner findLoggedRestaurantOwner() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var loggedEmail = userJpaRepository.findByUsername(username).getEmail();
        return restaurantOwnerJpaRepository.findByEmail(loggedEmail)
                .map(restaurantOwnerEntityMapper::mapFromEntity)
                .orElseThrow(() -> new SecurityException(
                        "Error during looking for user with email: [%s] and username: [%s]"
                                .formatted(loggedEmail, username)
                ));
    }
}
