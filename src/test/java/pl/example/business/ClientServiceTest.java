package pl.example.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.business.dao.ClientDAO;
import pl.example.domain.User;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static pl.example.util.DomainInput.kindOfClient;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientDAO clientDAO;


    private ClientService clientService;

//
//    @Test
//    void saveClient() {
//        Client client = kindOfClient();
//        when(clientDAO.saveClient(client)).thenReturn(client);
//
//        Client savedClient = clientService.saveClient(client);
//
//        assertEquals("jan.testowy@gmail.com", savedClient.getEmail());
//        verify(clientDAO, times(1)).saveClient(client);
//
//    }
//
//    @Test
//    void findLoggedClient() {
//        Client client = kindOfClient().withName("Franek");
//        when(clientDAO.findLoggedClient()).thenReturn(client);
//
//        Client loggedClient = clientService.findLoggedClient();
//
//        Assertions.assertThat(client).isEqualTo(loggedClient);
//
//    }
}