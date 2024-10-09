package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.ClientDAO;
import pl.example.domain.Client;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientDAO clientDAO;


    public Client findClientById(Integer id) {
        return clientDAO.findById(id).orElseThrow();
    }

    @Transactional
    public Client saveClient(Client client) {
        return clientDAO.saveClient(client);

    }

    public Client findLoggedClient(){
        return clientDAO.findLoggedClient();
    }
}
