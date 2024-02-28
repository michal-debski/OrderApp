package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.StreetDAO;
import pl.example.domain.Meal;
import pl.example.domain.Restaurant;
import pl.example.domain.Street;
import pl.example.domain.exception.NotFoundException;

import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class StreetService {

    private final StreetDAO streetDAO;
    public List<Street> findAll() {
        List<Street> streets = streetDAO.findAll();
        log.info("Available restaurants: [{}]", streets.size());
        return streets;
    }
    @Transactional
    public List<Street> findAllByRestaurantId(Integer id) {
        List<Street> streetsForSelectedRestaurant = streetDAO.findAllByRestaurantId(id);
        if (streetsForSelectedRestaurant.size() == 0) {
            throw new NotFoundException("Could not find streets for selected Restaurant [%s]".formatted(id));
        }
        return streetsForSelectedRestaurant;
    }

}
