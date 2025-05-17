package pl.example.business;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
//    @Mock
//    private ClientService clientService;
//
//    @Mock
//    private RestaurantService restaurantService;
//
//    @Mock
//    private OrderDAO orderDAO;
//
//    @InjectMocks
//    private OrderService orderService;
//
//
//    @Test
//    void shouldDeleteOrder() {
//
//        Order order = kindOfOrder();
//
//        orderService.deleteOrder(order);
//
//        verify(orderDAO).deleteOrder(order);
//    }
//
//    @Test
//    void shouldSave() {
//        Order order = kindOfOrder();
//
//        when(orderDAO.saveOrder(order)).thenReturn(order);
//
//        Order result = orderService.saveOrder(order);
//
//        assertEquals(order,result);
//
//
//    }
//
//    @Test
//    void shouldFindByOrderNumber() {
//        Order order = kindOfOrder().withOrderNumber("00000");
//
//        when(orderDAO.findOrderByOrderNumber("00000")).thenReturn(Optional.of(order));
//
//        Optional<Order> byOrderNumber = orderService.findByOrderNumber("00000");
//
//        Assertions.assertThat(byOrderNumber).isPresent();
//        assertEquals(order, byOrderNumber.get());
//    }
//
//    @Test
//    void shouldFindByClientId() {
//        Integer clientId = 1;
//        Order order = kindOfOrder();
//        Order secondOrder = kindOfOrder().withRestaurant(kindOfRestaurant1());
//        when(orderDAO.findOrderByClientId(clientId)).thenReturn(List.of(order,secondOrder));
//
//        List<Order> orderList = orderService.findOrderByClientEmail(clientId);
//
//        assertEquals(2,orderList.size());
//
//    }

//    @Test
//    void shouldBuildOrder() {
//        Integer restaurantId = 1;
//        Restaurant restaurant = kindOfRestaurant();
//        Client client = kindOfClient();
//        Order expected = Order.builder()
//                .orderNumber("12345")
//                .status("Preparing by chef")
//                .orderDate(OffsetDateTime.now())
//                .client(client)
//                .restaurant(restaurant)
//                .build();
//
//        when(clientService.findLoggedClient()).thenReturn(client);
//        when(restaurantService.findRestaurantById(restaurantId)).thenReturn(restaurant);
//        when(orderDAO.saveOrder(any(Order.class))).thenReturn(expected);
//
//        Order createdOrder = orderService.buildOrder(restaurantId, selectedMeals, quantity);
//
//        assertEquals(expected, createdOrder);
//    }

//    @Test
//    void shouldGetTotalOrderPrice() {
//        Integer orderId = 1;
//        BigDecimal expectedPrice = new BigDecimal("99.99");
//
//        when(orderDAO.getTotalOrderPrice(orderId)).thenReturn(expectedPrice);
//
//
//        BigDecimal actualPrice = orderService.getTotalOrderPrice(orderId);
//
//
//        assertEquals(expectedPrice, actualPrice);
//        verify(orderDAO).getTotalOrderPrice(orderId);
//    }
//
//    @Test
//    void shouldFindAll() {
//        List<Order> expectedOrders = List.of(kindOfOrder(), kindOfOrder1());
//
//        when(orderDAO.findAll()).thenReturn(expectedOrders);
//
//
//        List<Order> actualOrders = orderService.findAllOrders();
//
//
//        assertEquals(expectedOrders, actualOrders);
//        verify(orderDAO).findAll();
//    }
//
//    @Test
//    void shouldUpdateOrder() {
//        Order order = kindOfOrder();
//
//        order.setOrderNumber("12345");
//        order.setStatus("Delivered");
//
//        orderService.updateOrder(order);
//
//
//        verify(orderDAO).updateOrder(order);
//    }
}