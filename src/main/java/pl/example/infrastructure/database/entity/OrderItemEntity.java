package pl.example.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItemEntity {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer orderItemId;

    @Column(name = "quantity")
    private Integer quantity;


    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cart;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private MealEntity meal;


}
