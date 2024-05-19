package pl.example.infrastructure.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.example.domain.Client;
import pl.example.infrastructure.database.entity.ClientEntity;
import pl.example.infrastructure.database.repository.jpa.ClientJpaRepository;
import pl.example.infrastructure.database.repository.mapper.ClientEntityMapper;
import pl.example.infrastructure.security.UserEntity;
import pl.example.infrastructure.security.UserJpaRepository;
import pl.example.util.DomainInput;
import pl.example.util.EntityInput;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientRepositoryTest {
    @Mock
    private ClientJpaRepository clientJpaRepository;

    @Mock
    private ClientEntityMapper clientEntityMapper;
    @Mock
    private UserJpaRepository userJpaRepository;
    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;
    @InjectMocks
    private ClientRepository clientRepository;

    private UserEntity userEntity;
    private ClientEntity clientEntity;
    private Client client;

    @BeforeEach
    public void setUp() {
        clientEntity = EntityInput.kindOfClientEntity();
        client = DomainInput.kindOfClient();
        userEntity = EntityInput.kindOfUserEntity();
    }

    @Test
    void shouldFindByEmail() {
        when(clientJpaRepository.findByEmail("jan.testowy@gmail.com")).thenReturn(Optional.of(clientEntity));
        when(clientEntityMapper.mapFromEntity(clientEntity)).thenReturn(client);

        Optional<Client> clientFound = clientRepository.findByEmail("jan.testowy@gmail.com");

        org.assertj.core.api.Assertions.assertThat(clientFound).isPresent();
        assertEquals(client, clientFound.get());

    }

    @Test
    void shouldSaveClient() {
        when(clientEntityMapper.mapToEntity(client)).thenReturn(clientEntity);
        when(clientJpaRepository.save(clientEntity)).thenReturn(clientEntity);
        when(clientEntityMapper.mapFromEntity(clientEntity)).thenReturn(client);

        Client savedClient = clientRepository.saveClient(client);

        assertEquals(client, savedClient);

        verify(clientEntityMapper, times(1)).mapToEntity(client);
        verify(clientJpaRepository, times(1)).save(clientEntity);
        verify(clientEntityMapper, times(1)).mapFromEntity(clientEntity);
    }


    @Test
    void shouldFindById() {
        when(clientJpaRepository.findById(1)).thenReturn(Optional.of(clientEntity));
        when(clientEntityMapper.mapFromEntity(clientEntity)).thenReturn(client);

        Optional<Client> clientById = clientRepository.findById(1);

        org.assertj.core.api.Assertions.assertThat(clientById).isPresent();
        Assertions.assertEquals(client, clientById.get());
    }

    @Test
    void shouldFindLoggedClient() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getName()).thenReturn(userEntity.getUsername());
        when(userJpaRepository.findByUsername(userEntity.getUsername())).thenReturn(userEntity);
        when(clientJpaRepository.findByEmail(userEntity.getEmail())).thenReturn(Optional.of(clientEntity));
        when(clientEntityMapper.mapFromEntity(clientEntity)).thenReturn(client);

        Client result = clientRepository.findLoggedClient();

        Assertions.assertEquals(client, result);

        verify(securityContext).getAuthentication();
        verify(authentication).getName();
        verify(userJpaRepository).findByUsername(userEntity.getUsername());
        verify(clientJpaRepository).findByEmail(userEntity.getEmail());
        verify(clientEntityMapper).mapFromEntity(clientEntity);
    }
    @Test
    void shouldNotFindLoggedClient_UserNotFound() {

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getName()).thenReturn(userEntity.getUsername());
        when(userJpaRepository.findByUsername(userEntity.getUsername())).thenReturn(userEntity);
        when(clientJpaRepository.findByEmail(userEntity.getEmail())).thenReturn(Optional.empty());

        Assertions.assertThrows(SecurityException.class, () -> clientRepository.findLoggedClient());


        verify(securityContext).getAuthentication();
        verify(authentication).getName();
        verify(userJpaRepository).findByUsername(userEntity.getUsername());
        verify(clientJpaRepository).findByEmail(userEntity.getEmail());
    }
}