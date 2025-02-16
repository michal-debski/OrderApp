package pl.example.business.dao;

import pl.example.domain.Client;

import java.util.Optional;

public interface ClientDAO {


    Optional<Client> findClientByEmail(String email);

    Client saveClient(Client client);

    Optional<Client> findClientById(Integer clientId);

    Client findLoggedClient();
}
