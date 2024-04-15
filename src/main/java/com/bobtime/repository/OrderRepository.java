package com.bobtime.repository;

import com.bobtime.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
