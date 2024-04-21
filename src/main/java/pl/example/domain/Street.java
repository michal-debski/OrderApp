package pl.example.domain;

import lombok.*;

@With
@Value
@Builder
public class Street {

    Integer streetId;

     String name;

     Restaurant restaurant;

}
