package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.User;
import pl.example.domain.UserRole;
import pl.example.infrastructure.security.RoleEntity;
import pl.example.infrastructure.security.UserEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    User mapFromEntity(UserEntity entity);

    UserEntity mapToEntity(User user);

    UserRole map(RoleEntity value);


}
