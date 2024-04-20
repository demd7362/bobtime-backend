package com.bobtime.controller;

import com.bobtime.common.enums.dialog.UserMessage;
import com.bobtime.dto.model.UserDTO;
import com.bobtime.dto.request.UserRequestDTO;
import com.bobtime.dto.response.ResponseDTO;
import com.bobtime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                .message(UserMessage.CREATE_SUCCESS)
                .build();
    }
    @GetMapping("/admin-info")
    public ResponseEntity<ResponseDTO> getAdminInformation(){
        var data = userService.getAdminInformation();
        return ResponseDTO.entityBuilder()
                .httpStatus(HttpStatus.OK)
                .data(data)
                .message(UserMessage.CREATE_SUCCESS)
                .build();
    }
}
