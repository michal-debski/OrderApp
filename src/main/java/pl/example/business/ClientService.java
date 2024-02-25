package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.ClientDAO;
import pl.example.domain.Client;
import pl.example.domain.exception.NotFoundException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientDAO clientDAO;

    @Transactional
    public void issueOrder(Client client) {
        clientDAO.issueOrder(client);
    }

    @Transactional
    public Client findByEmail(String email) {
        Optional<Client> client = clientDAO.findByEmail(email);
        if (client.isEmpty()) {
            throw new NotFoundException("Could not find client by email: [%s]".formatted(email));
        }
        return client.get();
    }


    @Transactional
    public Client saveClient(Client client) {
        return clientDAO.saveClient(client);
    }

}
