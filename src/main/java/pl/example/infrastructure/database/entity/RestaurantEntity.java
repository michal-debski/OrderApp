package pl.example.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {"restaurantId", "name"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<StreetEntity> streets;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_owner_id")
    private RestaurantOwnerEntity restaurantOwner;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<OrderEntity> orders;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<MealEntity> meals;
}
