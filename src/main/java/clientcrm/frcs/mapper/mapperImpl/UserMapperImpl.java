package clientcrm.frcs.mapper.mapperImpl;

import clientcrm.frcs.dto.UserDto;
import clientcrm.frcs.entities.User;
import clientcrm.frcs.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author
 * @Project
 */
@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .idUser(user.getIdUser())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .roleName(user.getRole().getRoleName())
                .build();
    }

    @Override
    public User toUser(UserDto userDto) {
        User user = new User();
        user.setIdUser(userDto.getIdUser());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        return user;
    }
}
