package pl.example.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public record UserDTO ( String username,
        String password,
        String name,
        String surname,
        @Pattern(regexp = "^[+]\\d{2}\\s\\d{3}\\s\\d{3}\\s\\d{3}$")
        String phone,
        @Email
        String email,
        String role){

    public Map<String, String> asMap() {
        Map<String, String> result = new HashMap<>();
        Optional.ofNullable(name).ifPresent(value -> result.put("name", value));
        Optional.ofNullable(surname).ifPresent(value -> result.put("surname", value));
        Optional.ofNullable(phone).ifPresent(value -> result.put("phone", value));
        Optional.ofNullable(email).ifPresent(value -> result.put("email", value));
        Optional.ofNullable(role).ifPresent(value -> result.put("role", value));
        Optional.ofNullable(username).ifPresent(value -> result.put("username", value));
        Optional.ofNullable(password).ifPresent(value -> result.put("password", value));
        return result;
    }
}
