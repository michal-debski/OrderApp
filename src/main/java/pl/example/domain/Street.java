package pl.example.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "streetId")
@ToString(of = {"streetId", "name"})
public class Street {

     Integer streetId;

     String name;
}
