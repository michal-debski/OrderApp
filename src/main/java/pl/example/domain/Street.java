package pl.example.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class Street {

    Integer streetId;

    String name;


}
