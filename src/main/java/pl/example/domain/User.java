package pl.example.domain;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.With;

@With
@Value
@Setter
@Builder
public class User {

    String username;
    String password;
    String name;
    String surname;
    String phone;
    String email;
    String role;

}
