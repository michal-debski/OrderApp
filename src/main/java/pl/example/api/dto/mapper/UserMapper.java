package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.example.api.dto.UserDTO;
import pl.example.domain.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default User map(UserDTO userDTO) {
        return User.builder()
                .phone(userDTO.phone())
                .name(userDTO.name())
                .password(userDTO.password())
                .build();
    }

}
