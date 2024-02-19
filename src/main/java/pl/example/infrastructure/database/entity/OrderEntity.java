package pl.example.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "new_order")
public class OrderEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_number", unique = true)
    private String orderNumber;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "order_date")
    private OffsetDateTime orderDate;
    @Column(name = "completed_date")
    private OffsetDateTime completedDate;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private Set<OrderItemEntity> orderItems;

}
