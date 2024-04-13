package com.bobtime.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    private String role;
    private LocalDateTime createdAt;
    private String product;
    private int price;
    private boolean isPaid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userNum", nullable = false)
    private User user;


}
