package com.bobtime.service;

import com.bobtime.common.enums.Role;
import com.bobtime.dto.request.UserRequestDTO;
import com.bobtime.entity.User;
import com.bobtime.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void join(UserRequestDTO request) {
        String name = request.getUser().getName();
        Role role = request.getUser().getRole();
        if(role == Role.ADMIN){
            Optional<User> optionalAdmin = userRepository.findByRole(Role.ADMIN);
            if(optionalAdmin.isPresent()){
                User user = optionalAdmin.get();
                user.setRole(Role.PARTICIPANT);
                userRepository.save(user);
            }
        }
        User user = userRepository.findByName(name)
                .map(u -> {
                    u.setRole(role);
                    return u;
                })
                .orElseGet(() -> User.builder()
                        .name(name)
                        .role(request.getUser().getRole())
                        .build());
        userRepository.save(user);
    }
}
