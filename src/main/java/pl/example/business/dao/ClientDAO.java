package pl.example.business.dao;

import pl.example.domain.Client;
import pl.example.infrastructure.database.entity.ClientEntity;
import pl.example.infrastructure.security.UserEntity;

import java.util.Optional;

public interface ClientDAO {


    Optional<Client> findByEmail(String email);
    void issueOrder(Client client);
    Client saveClient(Client client);
}
