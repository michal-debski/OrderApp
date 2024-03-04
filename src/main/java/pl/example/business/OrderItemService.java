package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.OrderItemDAO;
import pl.example.domain.OrderItem;

@Service
@AllArgsConstructor
public class OrderItemService {
    private final OrderItemDAO orderItemDAO;


    @Transactional
    public void save(OrderItem orderItem) {
        orderItemDAO.save(orderItem);
    }


}
