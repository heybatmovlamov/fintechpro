package az.edu.fintechpro.service;


import az.edu.fintechpro.dao.entity.UserEntity;
import az.edu.fintechpro.dao.repository.UserRepository;
import az.edu.fintechpro.exception.UserAlreadyExistsException;
import az.edu.fintechpro.exception.UserNotFoundException;
import az.edu.fintechpro.mapper.UserMapper;
import az.edu.fintechpro.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        log.info("Fetching all users");
        List<UserEntity> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()) {
            log.warn("No users found");
        }
        return userMapper.entityListToDtoList(allUsers);
    }

    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id) {
        log.info("Fetching user with ID: {}", id);
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found."));
        return Optional.of(userMapper.entityToDto(userEntity));
    }

    @Transactional
    public UserDto create(UserDto userDto) {
        log.info("Attempting to create user with ID: {}", userDto.getId());
        if (userRepository.existsById(userDto.getId())) {
            throw new UserAlreadyExistsException("User with ID" + userDto.getId() + " already exists.");
        }
        UserEntity userEntity = userMapper.dtoToEntity(userDto);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        log.info("User with ID {} created successfully.", userDto.getId());
        return userMapper.entityToDto(savedUserEntity);
    }

    @Transactional
    public UserDto update(Long id, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User with ID {} not found", id);
                    return new UserNotFoundException("User with ID " + id + " not found");
                });
        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setAge(userDto.getAge());
        userEntity.setPhoneNumber(userDto.getPhoneNumber());
        userEntity.setAddress(userDto.getAddress());
        userEntity.setFinCode(userDto.getFinCode());

        UserEntity updatedUserEntity = userRepository.save(userEntity);
        log.info("User updated successfully with ID: {}", id);
        return userMapper.entityToDto(updatedUserEntity);
    }

    @Transactional
    public Optional<UserDto> updatePassword(Long id, String password) {
        return userRepository.findById(id)
                .map(userEntity -> {
                    userEntity.setPassword(password);
                    UserEntity updatedEntity = userRepository.save(userEntity);
                    log.info("User password updated successfully for ID: {}", id);
                    return Optional.of(userMapper.entityToDto(updatedEntity));
                }).orElseThrow(() -> {
                    log.error("User with ID {} not found", id);
                    return new UserNotFoundException("User with ID " + id + " not found");
                });
    }

    @Transactional
    public void deleteById(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            log.info("User deleted with ID: {}", userId);
        } else {
            log.error("User with ID {} does not exist", userId);
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }
    }

    @Transactional
    public void deleteAll() {
        if (userRepository.count() == 0) {
            throw new UserNotFoundException("No users found to delete.");
        }
        userRepository.deleteAll();
        log.info("All users deleted");
    }
}
