package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.ClientDAO;
import pl.example.domain.Client;

@Service
@AllArgsConstructor
@Slf4j
public class ClientService {

    private final ClientDAO clientDAO;


    public Client findClientById(Integer id) {
        return clientDAO.findById(id).orElseThrow();
    }

    @Transactional
    public Client saveClient(Client client) {
        Client savedClient = clientDAO.saveClient(client);
        log.info("Saving client {}", savedClient);
        return savedClient;

    }

    public Client findLoggedClient(){
        return clientDAO.findLoggedClient();
    }
}
