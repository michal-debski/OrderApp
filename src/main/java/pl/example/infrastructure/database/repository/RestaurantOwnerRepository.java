package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.RestaurantOwnerDAO;
import pl.example.domain.User;
import pl.example.infrastructure.database.repository.mapper.UserEntityMapper;
import pl.example.infrastructure.security.UserEntity;
import pl.example.infrastructure.security.UserJpaRepository;

@Repository
@Slf4j
@AllArgsConstructor
public class RestaurantOwnerRepository implements RestaurantOwnerDAO {

    private final UserEntityMapper userEntityMapper;
    private final UserJpaRepository userJpaRepository;

    public User findRestaurantOwnerByEmail(String username) {
        UserEntity byUsername = userJpaRepository.findByUsername(username);
        return userEntityMapper.mapFromEntity(byUsername);
    }

    @Override
    public User saveRestaurantOwner(User restaurantOwner) {
        UserEntity toSave = userEntityMapper.mapToEntity(restaurantOwner);
        UserEntity saved = userJpaRepository.save(toSave);
        log.info("Restaurant Owner was created successfully with email: " + saved.getEmail());
        return userEntityMapper.mapFromEntity(saved);
    }

    @Override
    public User findLoggedRestaurantOwner() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userJpaRepository.findByUsername(username);
        return userEntityMapper.mapFromEntity(user);
    }
}
