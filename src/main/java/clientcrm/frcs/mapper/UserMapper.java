package clientcrm.frcs.mapper;

import clientcrm.frcs.dto.UserDto;
import clientcrm.frcs.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


import java.util.List;

/**
 * @author
 * @Project
 */
@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto userDto);
}
