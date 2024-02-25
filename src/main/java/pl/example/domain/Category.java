package pl.example.domain;

import lombok.*;
import pl.example.infrastructure.database.entity.MealEntity;

import java.util.Set;
@With
@Value
@Builder
@ToString(of = {"categoryId", "name"})
public class Category {
     Integer categoryId;
     String name;
     Set<MealEntity> meal;

}
