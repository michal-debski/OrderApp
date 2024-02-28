package pl.example.infrastructure.security;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.infrastructure.database.entity.ClientEntity;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void registerUser(RegistrationRequest request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRoles(user.getRoles());
        userRepository.save(user);
    }
}
