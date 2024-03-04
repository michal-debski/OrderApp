package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.ClientDAO;
import pl.example.domain.Client;
import pl.example.infrastructure.database.entity.ClientEntity;
import pl.example.infrastructure.database.repository.jpa.ClientJpaRepository;
import pl.example.infrastructure.database.repository.jpa.OrderJpaRepository;
import pl.example.infrastructure.database.repository.mapper.ClientEntityMapper;
import pl.example.infrastructure.database.repository.mapper.OrderEntityMapper;

import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ClientRepository implements ClientDAO {

    private final ClientJpaRepository clientJpaRepository;

    private final OrderJpaRepository orderJpaRepository;
    private final ClientEntityMapper clientEntityMapper;
    private final OrderEntityMapper orderEntityMapper;


    @Override
    public Optional<Client> findByEmail(String email) {
        return clientJpaRepository.findByEmail(email)
                .map(clientEntityMapper::mapFromEntity);
    }

    @Override
    public void issueOrder(Client client) {
        ClientEntity clientToSave = clientEntityMapper.mapToEntity(client);
        ClientEntity clientSaved = clientJpaRepository.saveAndFlush(clientToSave);

        client.getOrders().stream()
                .filter(order-> Objects.isNull(order.getOrderId()))
                .map(orderEntityMapper::mapToEntity)
                .forEach(orderEntity -> {
                    orderEntity.setClient(clientSaved);
                    orderJpaRepository.saveAndFlush(orderEntity);
                });

    }

    @Override
    public Client saveClient(Client client) {
        ClientEntity toSave = clientEntityMapper.mapToEntity(client);
        ClientEntity saved = clientJpaRepository.save(toSave);
        return clientEntityMapper.mapFromEntity(saved);
    }

    @Override
    public Optional<Client> findById(Integer clientId) {
        return clientJpaRepository.findById(clientId)
                .map(clientEntityMapper::mapFromEntity);
    }
}
