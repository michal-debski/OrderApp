package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.OrderItemDAO;
import pl.example.domain.Meal;
import pl.example.domain.Order;
import pl.example.domain.OrderItem;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OrderItemService {
    private final OrderItemDAO orderItemDAO;
    private final MealMenuService mealMenuService;


    @Transactional
    public void saveOrderItem(List<String> selectedMeals, Integer quantity, Order order) {
        List<Integer> mealIds = selectedMeals.stream()
                .map(Integer::valueOf)
                .toList();
        List<OrderItem> orderItems = new ArrayList<>();
        creatingOrderItemsListBasedOnOrderInfo(quantity, order, mealIds, orderItems);
        log.info("Created list of order item: {} ", orderItems);
        saveAllOrderItemsFromList(orderItems);
        log.info("Saved order items: {}", orderItems);

    }

    @Transactional
    public void deleteOrderItemsByOrderId(Integer orderId) {
        orderItemDAO.deleteOrderItemsByOrderId(orderId);
        log.info("Deleted order items by orderId: {}", orderId);
    }

    private void creatingOrderItemsListBasedOnOrderInfo(
            Integer quantity,
            Order order,
            List<Integer> mealIds,
            List<OrderItem> orderItems
    ) {
        for (Integer meal : mealIds) {

            Meal mealTemp = mealMenuService.findMealById(meal).orElseThrow();

            OrderItem orderItemFromClient = OrderItem.builder()
                    .meal(mealTemp)
                    .quantity(quantity)
                    .order(order)
                    .build();
            orderItems.add(orderItemFromClient);
        }
    }

    private void saveAllOrderItemsFromList(List<OrderItem> orderItems) {
        for (OrderItem orderItemToSave : orderItems) {
            orderItemDAO.save(orderItemToSave);
        }
    }


}
