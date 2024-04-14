package pl.example.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "email")
@ToString(of = {"clientId", "name", "phone","surname", "email"})
public class Client {
    Integer clientId;
    String name;
    String surname;
    String phone;
    String email;

    Set<Order> orders;
}
