package pl.example.api.dto;

import lombok.Builder;


@Builder

public record ClientDTO(Integer clientId, String name, String surname, String phone, String email) {

}
