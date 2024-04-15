package com.bobtime.controller;

import com.bobtime.dto.request.RequestDTO;
import com.bobtime.dto.request.UserRequestDTO;
import com.bobtime.dto.response.ResponseDTO;
import com.bobtime.entity.User;
import com.bobtime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;


    @PostMapping("/join")
    public ResponseEntity<ResponseDTO> join(@RequestBody UserRequestDTO request){
        userService.join(request);
        return ResponseDTO.entityBuilder()
                .httpStatus(HttpStatus.OK)
                .message(() -> "등록 완료")
                .build();
    }
}
