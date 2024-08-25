package az.edu.fintechpro.controller;

import az.edu.fintechpro.model.dto.UserDto;
import az.edu.fintechpro.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll() {
        log.info("Handling request to fetch all users");
        List<UserDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> findById(@PathVariable @NotNull Long id) {
        log.info("Handling request to fetch user with ID: {}", id);
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) {
        log.info("Handling request to create a new user with ID: {}", userDto.getId());
        return ResponseEntity.ok(userService.create(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable @NotNull Long id, @RequestBody @Valid UserDto userDto) {
        log.info("Handling request to update user with ID: {}", id);
        return ResponseEntity.ok(userService.update(id, userDto));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<UserDto> updateUserPassword(@PathVariable @NotNull Long id, @RequestParam String password) {
        log.info("Handling request to update password for user with ID: {}", id);
        Optional<UserDto> updatedUser = userService.updatePassword(id, password);
        return updatedUser.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable @NotNull Long id) {
        log.info("Handling request to delete user with ID: {}", id);
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteAllUsers() {
        log.info("Handling request to delete all users");
        userService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}