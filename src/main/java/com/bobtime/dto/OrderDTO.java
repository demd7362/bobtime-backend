package com.bobtime.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long num;
    private String role;
    private LocalDateTime createdAt;
    private String product;
    private int price;
    private boolean isPaid;
}
