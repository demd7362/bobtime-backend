package com.bobtime.repository;

import com.bobtime.entity.Order;
import com.bobtime.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    Optional<Order> findByUserAndCreatedAtBetween(User user, LocalDateTime startOfDay , LocalDateTime endOfDay);
}
