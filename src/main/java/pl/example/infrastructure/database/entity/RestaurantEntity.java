package pl.example.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
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

    @Column(name = "name")
    private Long deliveryArea;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private RestaurantOwnerEntity restaurantOwner;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<MenuEntity> menus;
}
