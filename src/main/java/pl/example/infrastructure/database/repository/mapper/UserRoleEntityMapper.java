package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.UserRole;
import pl.example.infrastructure.security.RoleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleEntityMapper {

    UserRole mapFromEntity(RoleEntity entity);

    RoleEntity mapToEntity(UserRole userRole);
}
