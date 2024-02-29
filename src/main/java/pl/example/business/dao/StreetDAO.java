package pl.example.business.dao;

import pl.example.domain.Street;

import java.util.List;

public interface StreetDAO {

    List<Street> findAll();
    List<Street> findAllByRestaurantId(Integer id);

    void save(Street street);

    void deleteById(Integer streetId);
}
