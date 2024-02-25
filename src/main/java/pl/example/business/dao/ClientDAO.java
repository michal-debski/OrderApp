package pl.example.business.dao;

import pl.example.domain.Client;

import java.util.Optional;

public interface ClientDAO {

    Optional<Client> findByEmail(String email);

    void issueOrder(Client client);
    Client saveClient(Client client);
}
