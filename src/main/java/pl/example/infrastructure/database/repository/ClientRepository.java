package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.ClientDAO;
import pl.example.domain.Client;
import pl.example.infrastructure.database.entity.ClientEntity;
import pl.example.infrastructure.database.repository.jpa.ClientJpaRepository;
import pl.example.infrastructure.database.repository.jpa.OrderJpaRepository;
import pl.example.infrastructure.database.repository.mapper.ClientEntityMapper;
import pl.example.infrastructure.database.repository.mapper.OrderEntityMapper;
import pl.example.infrastructure.security.UserJpaRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ClientRepository implements ClientDAO {

    private final ClientJpaRepository clientJpaRepository;

    private final ClientEntityMapper clientEntityMapper;
    private final UserJpaRepository userJpaRepository;


    @Override
    public Optional<Client> findByEmail(String email) {
        return clientJpaRepository.findByEmail(email)
                .map(clientEntityMapper::mapFromEntity);
    }


    @Override
    public Client saveClient(Client client) {
        ClientEntity toSave = clientEntityMapper.mapToEntity(client);
        ClientEntity saved = clientJpaRepository.save(toSave);
        return clientEntityMapper.mapFromEntity(saved);
    }

    @Override
    public Optional<Client> findById(Integer clientId) {
        return clientJpaRepository.findById(clientId)
                .map(clientEntityMapper::mapFromEntity);
    }

    @Override
    public Client findLoggedClient() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var loggedEmail = userJpaRepository.findByUsername(username).getEmail();
        return clientJpaRepository.findByEmail(loggedEmail)
                .map(clientEntityMapper::mapFromEntity)
                .orElseThrow(() -> new SecurityException(
                        "Error during looking for user with email: [%s] and username: [%s]"
                                .formatted(loggedEmail, username)
                ));
    }
}
