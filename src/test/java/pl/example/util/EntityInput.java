package pl.example.util;

import lombok.experimental.UtilityClass;
import pl.example.infrastructure.database.entity.*;
import pl.example.infrastructure.security.UserEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@UtilityClass

public class EntityInput {


    public static RestaurantEntity kindOfRestaurantEntity() {
        return RestaurantEntity.builder()
                .restaurantId(4)
                .restaurantName("test1")
                .restaurantOwner(kindOfRestaurantOwnerEntity())
                .address(AddressEntity.builder()
                        .country("Poland")
                        .city("Warsaw")
                        .street("Testowa")
                        .number("1")
                        .build())
                .email("test1@gmail.com")
                .phone("+48 444 555 666")
                .build();
    }

    public static RestaurantEntity kindOfRestaurantEntity1() {
        return RestaurantEntity.builder()
                .restaurantId(5)
                .restaurantName("test2")
                .restaurantOwner(kindOfRestaurantOwnerEntity1())
                .address(AddressEntity.builder()
                        .country("Poland")
                        .city("Warsaw")
                        .street("Testowa")
                        .number("2")
                        .build())
                .email("test2@gmail.com")
                .phone("+48 555 666 777")
                .build();
    }

    public static RestaurantEntity kindOfRestaurantEntity2() {
        return RestaurantEntity.builder()
                .restaurantId(6)
                .restaurantName("test3")
                .restaurantOwner(kindOfRestaurantOwnerEntity())
                .address(AddressEntity.builder()
                        .country("Poland")
                        .city("Warsaw")
                        .street("Testowa")
                        .number("3")
                        .build())
                .email("test3@gmail.com")
                .phone("+48 666 777 888")
                .build();
    }

    private static RestaurantOwnerEntity kindOfRestaurantOwnerEntity1() {
        return RestaurantOwnerEntity.builder()
                .restaurantOwnerId(4)
                .name("Jan")
                .surname("Testowy")
                .email("adam.testowy@gmail.com")
                .phone("+48 222 333 444")
                .build();
    }

    public static RestaurantOwnerEntity kindOfRestaurantOwnerEntity() {
        return RestaurantOwnerEntity.builder()
                .restaurantOwnerId(3)
                .name("Adam")
                .surname("Testowy")
                .email("test@gmail.com")
                .phone("+48 111 222 333")
                .build();
    }

    public static StreetEntity kindOfStreetEntity() {
        return StreetEntity.builder()
                .streetId(1)
                .name("Testowa")
                .build();
    }

    public static StreetEntity kindOfStreetEntity1() {
        return StreetEntity.builder()
                .streetId(2)
                .name("Testowa2")
                .build();
    }

    public static RestaurantStreetEntity kindOfRestaurantStreetEntity() {
        return RestaurantStreetEntity.builder()
                .street(kindOfStreetEntity())
                .restaurant(kindOfRestaurantEntity())
                .build();
    }

    public static RestaurantStreetEntity kindOfRestaurantStreetEntity1() {
        return RestaurantStreetEntity.builder()
                .street(kindOfStreetEntity1())
                .restaurant(kindOfRestaurantEntity())
                .build();
    }

    public static ClientEntity kindOfClientEntity() {
        return ClientEntity.builder()
                .clientId(1)
                .name("Jan")
                .surname("Testowy")
                .email("jan.testowy@gmail.com")
                .phone("+48 777 888 999")
                .build();
    }

    private static ClientEntity kindOfClientEntity1() {
        return ClientEntity.builder()
                .clientId(2)
                .name("Adam")
                .surname("Testowy")
                .email("adam.testowy@gmail.com")
                .phone("+48 777 888 999")
                .build();
    }

    public static MealEntity kindOfMealEntity() {
        return MealEntity.builder()
                .mealId(1)
                .category(kindOfCategoryEntity())
                .description("test")
                .name("test")
                .restaurant(kindOfRestaurantEntity())
                .price(new BigDecimal("33"))
                .build();
    }

    public static MealEntity kindOfMealEntity1() {
        return MealEntity.builder()
                .mealId(2)
                .category(kindOfCategoryEntity())
                .description("test")
                .name("test")
                .restaurant(kindOfRestaurantEntity())
                .price(new BigDecimal("22"))
                .build();
    }

    private static CategoryEntity kindOfCategoryEntity() {
        return CategoryEntity.builder()
                .categoryId(1)
                .name("Main dish")
                .build();
    }

    public static OrderEntity kindOfOrderEntity() {
        return OrderEntity.builder()
                .orderId(1)
                .client(kindOfClientEntity())
                .orderNumber("123")
                .orderDate(OffsetDateTime.of(
                        2024, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC))
                .status("Preparing by chef")
                .restaurant(kindOfRestaurantEntity())
                .totalPrice(null)
                .build();
    }

    public static OrderEntity kindOfOrderEntity1() {
        return OrderEntity.builder()
                .orderId(2)
                .client(EntityInput.kindOfClientEntity1())
                .orderNumber("124")
                .orderDate(OffsetDateTime.of(
                        2024, 2, 2, 12, 0, 0, 0, ZoneOffset.UTC))
                .status("Preparing by chef")
                .restaurant(EntityInput.kindOfRestaurantEntity1())
                .totalPrice(new BigDecimal("55"))
                .build();

    }

    public static OrderItemEntity kindOfOrderItemEntity() {
        return OrderItemEntity.builder()
                .orderItemId(1)
                .order(kindOfOrderEntity())
                .meal(kindOfMealEntity())
                .quantity(1)
                .build();
    }

    public static OrderItemEntity kindOfOrderItemEntity1(OrderEntity order) {
        return OrderItemEntity.builder()
                .orderItemId(2)
                .order(EntityInput.kindOfOrderEntity())
                .meal(EntityInput.kindOfMealEntity())
                .quantity(1)
                .build();
    }


    public static UserEntity kindOfUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testUser");
        userEntity.setEmail("testUser@example.com");
        return userEntity;
    }


}
