package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.ClientDAO;
import pl.example.domain.User;

@Service
@AllArgsConstructor
@Slf4j
public class ClientService {

    private final ClientDAO clientDAO;


    public User findClientById(Integer id) {
        return clientDAO.findClientById(id).orElseThrow();
    }

    @Transactional
    public User saveClient(User client) {
        User savedClient = clientDAO.saveClient(client);
        log.info("Saving client {}", savedClient);
        return savedClient;

    }

    public User findLoggedClient(){
        return clientDAO.findLoggedClient();
    }
}
