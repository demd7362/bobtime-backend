package com.bobtime.dto.model;

import com.bobtime.common.utils.DateUtils;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long num;
    private String role;
    private String productName;
    private int price;
    private boolean isPaid;
    private LocalDateTime paidAt;
    private UserDTO user;

    /* Defaults */

    @Builder.Default
    private LocalDateTime createdAt = DateUtils.current();
}
