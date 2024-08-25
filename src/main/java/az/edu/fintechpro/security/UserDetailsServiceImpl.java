package az.edu.fintechpro.security;

import az.edu.fintechpro.dao.entity.UserEntity;
import az.edu.fintechpro.dao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String findCode)  {
        Optional<UserEntity> user = Optional.ofNullable(userRepository.findByFinCode(findCode)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by finCode")));
        return new User(
                user.get().getEmail(),user.get().getPassword(),new ArrayList<>());
    }
}
