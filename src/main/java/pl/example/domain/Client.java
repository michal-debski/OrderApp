package pl.example.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "email")
@ToString(of = {"clientId", "name", "surname", "email"})
public class Client {
    Integer clientId;
    String name;
    String surname;
    String phone;
    String email;

    Set<Cart> carts;
    Set<Order> orders;
}
