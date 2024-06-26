package com.bobtime.dto.model;

import com.bobtime.common.enums.Role;
import com.bobtime.common.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long num;
    private String name;
    private Role role;
    @Builder.Default
    private LocalDateTime createdAt = DateUtils.current();
}
