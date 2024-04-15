package com.bobtime.service;

import com.bobtime.common.utils.DateUtils;
import com.bobtime.common.utils.EntityUtils;
import com.bobtime.dto.model.OrderDTO;
import com.bobtime.dto.model.UserDTO;
import com.bobtime.dto.request.OrderRequestDTO;
import com.bobtime.entity.Order;
import com.bobtime.entity.User;
import com.bobtime.repository.OrderRepository;
import com.bobtime.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createOrder(OrderRequestDTO request) {
        OrderDTO orderDTO = request.getOrder();
        UserDTO userDTO = request.getUser();
        User user = userRepository.findByName(userDTO.getName()).orElseThrow();
        Order order = Order.builder()
                .price(orderDTO.getPrice())
                .user(user)
                .role(orderDTO.getRole())
                .productName(orderDTO.getProductName())
                .build();
        orderRepository.save(order);
    }

    public List<OrderDTO> getOrdersByDate(LocalDateTime dateTime) {
        LocalDate targetDate = LocalDate.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth());
        LocalDateTime startOfDay = targetDate.atStartOfDay();
        LocalDateTime endOfDay = targetDate.atStartOfDay().plusDays(1);
        List<Order> orders = orderRepository.findAllByCreatedAtBetween(startOfDay, endOfDay);
        return orders.stream().map(order -> {
            OrderDTO orderDTO = EntityUtils.copyObject(order, OrderDTO.class);
            orderDTO.setUser(EntityUtils.copyObject(order.getUser(), UserDTO.class));
            return orderDTO;
        }).toList();
    }

    public void togglePaid(long orderNum) {
        Order order = orderRepository.findById(orderNum).orElseThrow();
        order.setPaid(!order.isPaid());
        if(order.isPaid()){
            order.setPaidAt(DateUtils.current());
        }
        orderRepository.save(order);
    }
}
