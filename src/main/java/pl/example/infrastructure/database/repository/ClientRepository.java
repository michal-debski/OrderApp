package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.ClientDAO;
import pl.example.domain.User;
import pl.example.infrastructure.database.repository.mapper.UserEntityMapper;
import pl.example.infrastructure.security.UserEntity;
import pl.example.infrastructure.security.UserJpaRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class ClientRepository implements ClientDAO {


    private final UserEntityMapper userEntityMapper;
    private final UserJpaRepository userJpaRepository;


    @Override
    public Optional<User> findClientByEmail(String email) {
        UserEntity byUsername = userJpaRepository.findByUsername(email);
        return Optional.of(userEntityMapper.mapFromEntity(byUsername));
    }


    @Override
    public User saveClient(User client) {
        UserEntity toSave = userEntityMapper.mapToEntity(client);
        UserEntity saved = userJpaRepository.save(toSave);
        return userEntityMapper.mapFromEntity(saved);
    }

    @Override
    public Optional<User> findClientById(Integer clientId) {
        return userJpaRepository.findById(clientId)
                .map(userEntityMapper::mapFromEntity);
    }

    @Override
    public User findLoggedClient() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity byUsername = userJpaRepository.findByUsername(username);
        if(byUsername != null) {
            return userEntityMapper.mapFromEntity(byUsername);
        }else {
            log.error("User not logged in");
            throw new RuntimeException("User not logged in");
        }
    }
}
