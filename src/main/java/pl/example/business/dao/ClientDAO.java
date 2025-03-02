package pl.example.business.dao;

import pl.example.domain.User;

import java.util.Optional;

public interface ClientDAO {


    Optional<User> findClientByEmail(String email);

    User saveClient(User client);

    Optional<User> findClientById(Integer clientId);

    User findLoggedClient();
}
