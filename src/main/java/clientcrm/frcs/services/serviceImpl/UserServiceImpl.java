package clientcrm.frcs.services.serviceImpl;

import clientcrm.frcs.Exceptions.UserNotFoundException;
import clientcrm.frcs.dto.UserDto;

import clientcrm.frcs.entities.User;
import clientcrm.frcs.mapper.InvoiceMapper;
import clientcrm.frcs.mapper.UserMapper;
import clientcrm.frcs.repositories.UserRepository;
import clientcrm.frcs.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author
 * @Project
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final InvoiceMapper invoiceMapper;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }


    @Override
    public UserDto getUserById(Long idUser) {

        Optional<User> user = userRepository.findById(idUser);

        return userMapper.toUserDto(user.orElseThrow(()-> new UserNotFoundException(idUser)));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }

    @Override
    @Transactional
    public void updateUser(Long id, String firstName, String lastName, String email, String phone) {

        User updateUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));

        updateUser.setIdUser(id);
        updateUser.setEmail(email);
        updateUser.setFirstName(firstName);
        updateUser.setLastName(lastName);
        updateUser.setPhone(phone);

    }


    @Override
    @Transactional
    public void deleteUser(long id) {
        // Find the user by ID
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        // Delete the user
        userRepository.delete(user);
    }


}
