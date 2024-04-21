package pl.example.business.dao;

import pl.example.domain.Client;

import java.util.Optional;

public interface ClientDAO {


    Optional<Client> findByEmail(String email);

    Client saveClient(Client client);

    Optional<Client> findById(Integer clientId);

    Client findLoggedClient();
}
