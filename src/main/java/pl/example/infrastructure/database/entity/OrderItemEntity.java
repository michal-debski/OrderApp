package pl.example.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "orderItemId")
@ToString(of = {"orderItemId", "quantity"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer orderItemId;
    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private MealEntity meal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

}
