package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.StreetDAO;
import pl.example.domain.Street;
import pl.example.infrastructure.database.repository.jpa.StreetJpaRepository;
import pl.example.infrastructure.database.repository.mapper.StreetEntityMapper;

@Repository
@AllArgsConstructor
public class StreetRepository implements StreetDAO {

    private final StreetJpaRepository streetJpaRepository;
    private final StreetEntityMapper streetEntityMapper;

    @Override
    public Page<Street> findAll(Pageable pageable) {
        return streetJpaRepository.findAll(pageable)
                .map(streetEntityMapper::mapFromEntity);

    }

    @Override
    public Street findById(Integer streetId) {
        return streetJpaRepository.findById(streetId)
                .map(streetEntityMapper::mapFromEntity).orElseThrow();
    }
}
