package id.mitrais.registration.mapper;

import id.mitrais.registration.dto.RegistrationDto;
import id.mitrais.registration.model.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author gpk
 */
@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    @Mappings({
            @Mapping(target = "createdAt", source = "entity.createdAt"),
            @Mapping(target = "updatedAt", source = "entity.lastModifiedDate"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "updatedBy", source = "entity.lastModifiedBy"),})
    RegistrationDto entityToDto(Registration entity);

    Registration dtoToEntity(RegistrationDto entity);
}


