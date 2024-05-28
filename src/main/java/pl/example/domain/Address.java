package pl.example.domain;

import lombok.*;

@With
@Value
@Builder
@Setter
@EqualsAndHashCode
@ToString
public class Address {
    String country;
    String city;
    String street;
    String number;
}
