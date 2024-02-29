package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.StreetDAO;
import pl.example.domain.Street;
import pl.example.infrastructure.database.repository.jpa.StreetJpaRepository;
import pl.example.infrastructure.database.repository.mapper.StreetEntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class StreetRepository implements StreetDAO {

    private final StreetJpaRepository streetJpaRepository;
    private final StreetEntityMapper streetEntityMapper;
    @Override
    public List<Street> findAll() {
        return streetJpaRepository.findAll().stream()
                .map(streetEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Street> findAllByRestaurantId(Integer id) {
        return streetJpaRepository.findAllByRestaurantId(id).stream()
                .map(streetEntityMapper::mapFromEntity).collect(Collectors.toList());
    }

    @Override
    public void save(Street street) {
        streetJpaRepository.save(streetEntityMapper.mapToEntity(street));
    }

    @Override
    public void deleteById(Integer streetId) {
        streetJpaRepository.delete(streetJpaRepository.findById(streetId).get());
    }
}
