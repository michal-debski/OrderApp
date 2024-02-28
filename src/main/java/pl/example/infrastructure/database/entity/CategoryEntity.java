package pl.example.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "categoryId")
@ToString(of = {"categoryId", "name", "meals"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name ="name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<MealEntity> meals;


}

