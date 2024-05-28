package pl.example.util;

import lombok.experimental.UtilityClass;
import pl.example.domain.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class DomainInput {


    public static Restaurant kindOfRestaurant() {
        return Restaurant.builder()
                .restaurantId(4)
                .restaurantName("test1")
                .restaurantOwner(kindOfRestaurantOwner())
                .address(Address.builder()
                        .country("Poland")
                        .city("Warsaw")
                        .street("Testowa")
                        .number("1")
                        .build())
                .email("test1@gmail.com")
                .phone("+48 444 555 666")
                .build();
    }

    public static Restaurant kindOfRestaurant1() {
        return Restaurant.builder()
                .restaurantId(5)
                .restaurantName("test2")
                .restaurantOwner(kindOfRestaurantOwner1())
                .address(Address.builder()
                        .country("Poland")
                        .city("Warsaw")
                        .street("Testowa")
                        .number("2")
                        .build())
                .email("test2@gmail.com")
                .phone("+48 555 666 777")
                .build();
    }

    public static Restaurant kindOfRestaurant2() {
        return Restaurant.builder()
                .restaurantId(6)
                .restaurantName("test2")
                .restaurantOwner(kindOfRestaurantOwner())
                .address(Address.builder()
                        .country("Poland")
                        .city("Warsaw")
                        .street("Testowa")
                        .number("3")
                        .build())
                .email("test3@gmail.com")
                .phone("+48 666 777 888")
                .build();
    }

    public static RestaurantOwner kindOfRestaurantOwner1() {
        return RestaurantOwner.builder()
                .restaurantOwnerId(4)
                .name("Jan")
                .surname("Testowy")
                .email("adam.testowy@gmail.com")
                .phone("+48 222 333 444")
                .build();
    }

    public static RestaurantOwner kindOfRestaurantOwner() {
        return RestaurantOwner.builder()
                .restaurantOwnerId(3)
                .name("Adam")
                .surname("Testowy")
                .email("test@gmail.com")
                .phone("+48 111 222 333")
                .build();
    }

    public static Street kindOfStreet() {
        return Street.builder()
                .streetId(1)
                .name("Testowa")
                .build();
    }

    public static Street kindOfStreet1() {
        return Street.builder()
                .streetId(2)
                .name("Testowa2")
                .build();
    }

    public static RestaurantStreet kindOfRestaurantStreet() {
        return RestaurantStreet.builder()
                .street(kindOfStreet())
                .restaurant(kindOfRestaurant())
                .build();
    }

    public static Client kindOfClient() {
        return Client.builder()
                .clientId(1)
                .name("Jan")
                .surname("Testowy")
                .email("jan.testowy@gmail.com")
                .phone("+48 777 888 999")
                .build();
    }

    private static Client kindOfClient1() {
        return Client.builder()
                .clientId(2)
                .name("Adam")
                .surname("Testowy")
                .email("adam.testowy@gmail.com")
                .phone("+48 777 888 999")
                .build();
    }

    public static Meal kindOfMeal() {
        return Meal.builder()
                .mealId(1)
                .category(kindOfCategory())
                .description("test")
                .name("test")
                .restaurant(kindOfRestaurant())
                .price(new BigDecimal("33"))
                .build();
    }

    public static Meal kindOfMeal1() {
        return Meal.builder()
                .mealId(2)
                .category(kindOfCategory())
                .description("test")
                .name("test")
                .restaurant(kindOfRestaurant())
                .price(new BigDecimal("22"))
                .build();
    }

    public static Category kindOfCategory() {
        return Category.builder()
                .categoryId(1)
                .name("Main dish")
                .build();
    }

    public static Order kindOfOrder() {
        return Order.builder()
                .orderId(1)
                .client(kindOfClient())
                .orderNumber("123")
                .orderDate(OffsetDateTime.of(
                        2024, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC))
                .status("Preparing by chef")
                .restaurant(kindOfRestaurant())
                .totalPrice(null)
                .build();
    }

    public static Order kindOfOrder1() {
        return Order.builder()
                .orderId(2)
                .client(DomainInput.kindOfClient1())
                .orderNumber("124")
                .orderDate(OffsetDateTime.of(
                        2024, 2, 2, 12, 0, 0, 0, ZoneOffset.UTC))
                .status("Preparing by chef")
                .restaurant(DomainInput.kindOfRestaurant1())
                .totalPrice(new BigDecimal("55"))
                .build();

    }

    public static OrderItem kindOfOrderItem() {
        return OrderItem.builder()
                .orderItemId(1)
                .order(kindOfOrder())
                .meal(kindOfMeal())
                .quantity(1)
                .build();
    }

    public static OrderItem kindOfOrderItem1() {
        return OrderItem.builder()
                .orderItemId(2)
                .order(DomainInput.kindOfOrder())
                .meal(DomainInput.kindOfMeal())
                .quantity(1)
                .build();
    }

    public static RestaurantStreet kindOfRestaurantStreet1() {
        return RestaurantStreet.builder()
                .street(kindOfStreet1())
                .restaurant(kindOfRestaurant())
                .build();
    }

    public static User kindOfUser() {
        return User.builder()
                .name("Jan")
                .surname("Nowak")
                .phone("+48 123 123 123")
                .username("testUser")
                .email("testUser@example.com")
                .build();
    }

}
