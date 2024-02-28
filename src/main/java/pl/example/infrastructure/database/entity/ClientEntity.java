package pl.example.infrastructure.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode(of = "clientId")
@ToString(of = {"clientId","name", "surname", "email"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer clientId;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phone")
    private String phone;
    @Email
    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private Set<CartEntity> carts;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private Set<OrderEntity> orders;

}
