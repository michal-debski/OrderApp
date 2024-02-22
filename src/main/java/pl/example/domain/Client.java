package pl.example.domain;

import lombok.*;

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
    Address address;

    Cart cart;
}
