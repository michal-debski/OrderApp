package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.business.dao.ClientDAO;
import pl.example.domain.Client;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientDAO clientDAO;


    public Client findById(Integer id) {
        return clientDAO.findById(id).orElseThrow();
    }
}
