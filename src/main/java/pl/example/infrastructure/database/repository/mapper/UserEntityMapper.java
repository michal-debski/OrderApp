package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.User;
import pl.example.infrastructure.security.UserEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {


    default User mapFromEntity(UserEntity entity) {
        return User.builder()
                .email(entity.getEmail())
                .password(entity.getPassword())
                .username(entity.getUsername())
                .role(entity.getRoles().stream().findFirst().get().getRole())
                .build();
    }
}
