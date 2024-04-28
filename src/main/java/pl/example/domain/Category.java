package pl.example.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "categoryId")
@ToString(of = {"categoryId", "name"})
public class Category {
    Integer categoryId;
    String name;


}
