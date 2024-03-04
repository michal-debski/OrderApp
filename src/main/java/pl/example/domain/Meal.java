package pl.example.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "mealId")
@ToString(of = {"mealId", "name", "category", "description", "price"})
public class Meal {
    Integer mealId;
    String name;

    Category category;

    String description;

    BigDecimal price;

    Restaurant restaurant;
    Set<OrderItem> orderItems;

}
