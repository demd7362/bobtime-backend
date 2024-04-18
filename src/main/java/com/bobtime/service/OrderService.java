package com.bobtime.service;

import com.bobtime.common.exception.ResponseException;
import com.bobtime.common.model.Dialog;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    /**
     *
     * @param request
     * @return true -> merged, false -> inserted
     */
    @Transactional
    public boolean mergeOrder(OrderRequestDTO request) {
        OrderDTO orderDTO = request.getOrder();
        UserDTO userDTO = request.getUser();

        User user = userRepository.findByName(userDTO.getName())
                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, () -> new Dialog("잘못된 접근","사용자가 등록되어 있지 않습니다.")));

        LocalDate targetDate = LocalDate.now();
        LocalDateTime startOfDay = targetDate.atStartOfDay();
        LocalDateTime endOfDay = targetDate.atStartOfDay().plusDays(1);
        AtomicBoolean isMerged = new AtomicBoolean(false);
        Order order = orderRepository.findByUserAndCreatedAtBetween(user, startOfDay, endOfDay)
                .map(o -> {
                    o.setPrice(orderDTO.getPrice());
                    o.setProductName(orderDTO.getProductName());
                    isMerged.set(true);
                    return o;
                }).orElseGet(() -> {
                    isMerged.set(false);
                    return Order.builder()
                            .price(orderDTO.getPrice())
                            .user(user)
                            .productName(orderDTO.getProductName())
                            .build();
                });
        orderRepository.save(order);
        return isMerged.get();
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

    /**
     *
     * @param orderNum
     * @return true -> paid false -> not paid yet
     */
    @Transactional
    public boolean togglePaidByOrderNum(long orderNum) {
        Order order = orderRepository.findById(orderNum).orElseThrow();
        order.setPaid(!order.isPaid());
        if(order.isPaid()){
            order.setPaidAt(DateUtils.current());
        }
        orderRepository.save(order);
        return order.isPaid();
    }

    public void setPaidAsTrueByUserName(String userName) {
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, () -> new Dialog("잘못된 접근","사용자가 등록되어 있지 않습니다.")));
        LocalDate targetDate = LocalDate.now();
        LocalDateTime startOfDay = targetDate.atStartOfDay();
        LocalDateTime endOfDay = targetDate.atStartOfDay().plusDays(1);
        Order order = orderRepository.findByUserAndCreatedAtBetween(user, startOfDay, endOfDay).orElseThrow();
        order.setPaid(true);
        orderRepository.save(order);
    }
}
