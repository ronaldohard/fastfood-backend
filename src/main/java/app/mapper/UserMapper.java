
package app.mapper;

import app.domain.dto.UserDTO;
import app.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDTO dto);

    UserDTO toDTO(User entity);

    List<UserDTO> toDTOList(List<User> entities);
}
