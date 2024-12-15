package pl.example.api.dto;

import lombok.Builder;
import lombok.With;

@With
@Builder

public record AddressDTO(String country, String city, String street, String number) {

}
