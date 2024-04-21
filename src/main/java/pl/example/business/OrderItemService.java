package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.api.dto.mapper.MealMapper;
import pl.example.business.dao.OrderItemDAO;
import pl.example.domain.Meal;
import pl.example.domain.Order;
import pl.example.domain.OrderItem;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemService {
    private final OrderItemDAO orderItemDAO;
    private final MealMenuService mealMenuService;


    @Transactional
    public void save(List<String> selectedMeals, Integer quantity, Order order) {
        List<Integer> mealIds = selectedMeals.stream()
                .map(Integer::valueOf)
                .toList();
        List<OrderItem> orderItems = new ArrayList<>();
        for (Integer meal : mealIds) {

            Meal mealTemp = mealMenuService.findMealById(meal).orElseThrow();

            OrderItem orderItemFromClient = OrderItem.builder()
                    .meal(mealTemp)
                    .quantity(quantity)
                    .order(order)
                    .build();
            orderItems.add(orderItemFromClient);
        }
        for (OrderItem orderItemToSave : orderItems) {
            orderItemDAO.save(orderItemToSave);
        }

    }


}
