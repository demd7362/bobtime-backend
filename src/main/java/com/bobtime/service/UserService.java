package com.bobtime.service;

import com.bobtime.common.enums.UserMessage;
import com.bobtime.common.exception.ResponseException;
import com.bobtime.dto.request.RequestDTO;
import com.bobtime.dto.model.UserDTO;
import com.bobtime.dto.request.UserRequestDTO;
import com.bobtime.entity.User;
import com.bobtime.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void join(UserRequestDTO request) {
        String name = request.getUser().getName();
        Optional<User> optionalUser = userRepository.findByName(name);
        if (optionalUser.isPresent()) {
            throw new ResponseException(HttpStatus.CONFLICT, UserMessage.CONFLICT);
        }
        try {
            User user = User.builder()
                    .name(name)
                    .build();
            userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseException(HttpStatus.BAD_REQUEST, e::getMessage);
        }
    }
}
