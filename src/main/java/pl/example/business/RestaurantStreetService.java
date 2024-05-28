package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.RestaurantStreetDAO;
import pl.example.domain.Restaurant;
import pl.example.domain.RestaurantStreet;
import pl.example.domain.Street;
import pl.example.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import pl.example.infrastructure.database.repository.mapper.StreetEntityMapper;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RestaurantStreetService {

    private final RestaurantStreetDAO restaurantStreetDAO;

    private final StreetService streetService;

    public List<RestaurantStreet> findAll() {
        List<RestaurantStreet> restaurantStreets = restaurantStreetDAO.findAll();
        log.info("Available streets: [{}]", restaurantStreets.size());
        return restaurantStreets;
    }


    @Transactional
    public void save(Restaurant restaurant, List<String> streets) {

        List<Integer> streetsId = streets.stream()
                .map(Integer::valueOf)
                .toList();
        List<RestaurantStreet> restaurantStreetsList = new ArrayList<>();
        for (Integer streetId : streetsId) {

            Street street = streetService.findById(streetId);
            RestaurantStreet restaurantStreet = RestaurantStreet
                    .builder()
                    .street(street)
                    .restaurant(restaurant)
                    .build();
            restaurantStreetsList.add(restaurantStreet);

        }
        for (RestaurantStreet restaurantStreet : restaurantStreetsList) {

            restaurantStreetDAO.save(restaurantStreet);

        }


    }

}
