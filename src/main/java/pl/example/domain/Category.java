package pl.example.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "categoryId")
@ToString(of = {"categoryId", "name", "meals"})
public class Category {
    Integer categoryId;
    String name;
    Set<Meal> meals;

}
