package pl.example.infrastructure.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.UserDAO;
import pl.example.domain.User;
import pl.example.infrastructure.database.repository.mapper.UserEntityMapper;

import java.util.Set;

@Slf4j
@Repository
@AllArgsConstructor
public class UserRepository implements UserDAO {

    public final PasswordEncoder passwordEncoder;
    private final UserJpaRepository userJpaRepository;
    private final RoleRepository roleRepository;

    private final UserEntityMapper userEntityMapper;

    @Override
    public User registerNewUser(User user) {
        RoleEntity role = roleRepository.findByRole(user.getRole());
        UserEntity userToSave = UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(Set.of(role))
                .build();
        UserEntity save = userJpaRepository.save(userToSave);
        log.info("New user was created: " + save);
        return userEntityMapper.mapFromEntity(save);
    }

}