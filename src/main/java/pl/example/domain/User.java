package pl.example.domain;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.With;

import java.util.Set;

@With
@Value
@Setter
@Builder
public class User {

    String id;
    String username;
    String password;
    String name;
    String surname;
    String phone;
    String email;
    Set<UserRole> roles;

}
