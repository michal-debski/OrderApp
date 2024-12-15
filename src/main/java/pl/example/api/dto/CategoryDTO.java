package pl.example.api.dto;

import lombok.Builder;

@Builder

public record CategoryDTO(Integer categoryId, String name) {
}
