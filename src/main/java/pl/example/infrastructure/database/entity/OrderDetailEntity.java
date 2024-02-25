package pl.example.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "orderDetailId")
@ToString(of = {"orderDetailId", "totalQuantity", "totalPrice", "meal", "orders"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="order_detail_id")
    private Integer orderDetailId;

    @Column (name = "total_quantity")
    private Integer totalQuantity;

    @Column (name = "total_price")
    private BigDecimal totalPrice;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "meal_id")
    private MealEntity meal;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderDetail")
    private Set<OrderEntity> orders;



}
