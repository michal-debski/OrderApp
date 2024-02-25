package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.CartDAO;
import pl.example.domain.Cart;
import pl.example.domain.OrderItem;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;


@Service
@AllArgsConstructor
public class CartService {

    private CartDAO cartDAO;
    private OrderService orderService;

    @Transactional
    public Cart saveCart(Cart cart) {
        return cartDAO.saveCart(cart);
    }

    @Transactional
    public void deleteCart(int cartId) {
        cartDAO.deleteCart(cartId);
    }

    @Transactional
    public Cart findCartById(int cartId) {
        return cartDAO.findCartById(cartId);
    }

    @Transactional
    public void addItemToCart(Cart cart, OrderItem orderItem) {

        Set<OrderItem> updatedOrderItems = new HashSet<>(cart.getOrderItems());
        updatedOrderItems.add(orderItem);

        Cart updatedCart = Cart.builder()
                .cartId(cart.getCartId())
                .orderItems(updatedOrderItems)
                .totalPrice(calculateTotalPrice(cart))
                .build();
        saveCart(updatedCart);

    }

    @Transactional
    public void cancelOrderForClient(Cart cart, String orderNumber) {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime orderDate = orderService.findByOrderNumber(orderNumber).get().getOrderDate();
        long minutesElapsed = ChronoUnit.MINUTES.between(orderDate, now);
        if (minutesElapsed < 20) {
            deleteCart(cart.getCartId());
        } else {
            throw new IllegalStateException("Order cannot be cancelled. Time elapsed: " + minutesElapsed + " minutes.");
        }

    }

    private BigDecimal calculateTotalPrice(Cart cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItem orderItem : cart.getOrderItems()) {
            totalPrice = totalPrice.add(orderItem.getPrice());
        }
        return totalPrice;
    }

}
