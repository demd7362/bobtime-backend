package com.bobtime.repository;

import com.bobtime.entity.Order;
import com.bobtime.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate, Sort sort);
    List<Order> findAllByisPaidAndProductNameIsNot(boolean isPaid, String productName);

    Optional<Order> findByUserAndCreatedAtBetween(User user, LocalDateTime startDate , LocalDateTime endDate);
    Optional<Order> findByUserAndCreatedAt(User user, LocalDateTime date);
}
