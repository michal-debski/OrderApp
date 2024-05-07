package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.example.api.dto.UserDTO;
import pl.example.domain.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserDTO userDTO);
}
