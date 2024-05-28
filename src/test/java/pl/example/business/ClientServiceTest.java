package pl.example.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.business.dao.ClientDAO;
import pl.example.domain.Client;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static pl.example.util.DomainInput.kindOfClient;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientDAO clientDAO;

    @InjectMocks
    private ClientService clientService;

    @Test
    void findById() {
        Client client = kindOfClient().withClientId(1);

        when(clientDAO.findById(1)).thenReturn(Optional.of(client));

        Client result = clientService.findById(client.getClientId());

        assertEquals(client, result);
    }

    @Test
    void saveClient() {
        Client client = kindOfClient();
        when(clientDAO.saveClient(client)).thenReturn(client);

        Client savedClient = clientService.saveClient(client);

        assertEquals("jan.testowy@gmail.com", savedClient.getEmail());
        verify(clientDAO, times(1)).saveClient(client);

    }

    @Test
    void findLoggedClient() {
        Client client = kindOfClient().withName("Franek");
        when(clientDAO.findLoggedClient()).thenReturn(client);

        Client loggedClient = clientService.findLoggedClient();

        Assertions.assertThat(client).isEqualTo(loggedClient);

    }
}