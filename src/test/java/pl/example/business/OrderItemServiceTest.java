package pl.example.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.business.dao.OrderItemDAO;
import pl.example.domain.Meal;
import pl.example.domain.Order;
import pl.example.domain.OrderItem;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static pl.example.util.DomainInput.*;

@ExtendWith(MockitoExtension.class)
class OrderItemServiceTest {
    @Mock
    private OrderItemDAO orderItemDAO;
    @Mock
    private MealMenuService mealMenuService;

    @InjectMocks
    private OrderItemService orderItemService;

    @Test
    void shouldSave() {

        Integer quantity = 2;
        Order order = kindOfOrder();

        Meal meal = kindOfMeal().withMealId(1);
        Meal secondMeal = kindOfMeal1().withMealId(2);
        List<String> selectedMeals = List.of(String.valueOf(meal.getMealId()), String.valueOf(secondMeal.getMealId()));

        when(mealMenuService.findMealById(1)).thenReturn(Optional.of(meal));
        when(mealMenuService.findMealById(2)).thenReturn(Optional.of(secondMeal));


        orderItemService.save(selectedMeals, quantity, order);


        verify(mealMenuService, times(2)).findMealById(anyInt());
        verify(orderItemDAO, times(2)).save(any(OrderItem.class));
    }

    @Test
    void shouldDeleteOrderItemsByOrderId() {
        Integer orderItemId = 1;

        orderItemService.deleteOrderItemsByOrderId(orderItemId);


        verify(orderItemDAO).deleteOrderItemsByOrderId(orderItemId);
    }
}