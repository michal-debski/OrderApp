package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.OrderDAO;
import pl.example.domain.Order;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderDAO orderDAO;


    @Transactional
    public void deleteOrder(Order order) {
        orderDAO.deleteOrder(order);
    }

    @Transactional
    public void save(Order order) {
        orderDAO.saveOrder(order);
    }


    public Optional<Order> findByOrderNumber(String orderNumber) {
        return orderDAO.findByOrderNumber(orderNumber);
    }


    public List<Order> findByRestaurantId(Integer id) {
        return orderDAO.findByRestaurantId(id);
    }


    @Transactional
    public List<Order> findByClientId(Integer clientId) {
        return orderDAO.findByClientId(clientId);
    }

}
