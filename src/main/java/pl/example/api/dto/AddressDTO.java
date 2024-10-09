package pl.example.api.dto;

import lombok.*;

@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public record AddressDTO (String country, String city, String street, String number){

}
