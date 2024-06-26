package pl.example.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of="restaurantOwnerId")
@ToString(of = {"restaurantOwnerId", "name", "surname","email","phone"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant_owner")
public class RestaurantOwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_owner_id")
    private Integer restaurantOwnerId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantOwner")
    private Set<RestaurantEntity> restaurants;
}
