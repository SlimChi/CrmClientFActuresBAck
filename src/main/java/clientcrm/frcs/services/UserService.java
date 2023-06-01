package clientcrm.frcs.services;

import clientcrm.frcs.dto.InvoiceDto;
import clientcrm.frcs.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author
 * @Project
 */
public interface UserService {
    List<UserDto> getUsers();

    UserDto getUserById(Long id);

    UserDto createUser(UserDto userDto);

    @Transactional
    void updateUser(Long id, String firstName, String lastName, String email, String phone);

    @Transactional
    void deleteUser(long id);


}
