package com.bobtime.entity;

import com.bobtime.common.utils.DateUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    private String productName;
    private int price;
    private boolean isPaid;
    private LocalDateTime paidAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userNum", nullable = false)
    private User user;
    /* Defaults */
    @Builder.Default
    private LocalDateTime createdAt = DateUtils.current();


}
