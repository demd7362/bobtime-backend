package com.bobtime.service;

import com.bobtime.common.enums.Role;
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
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private static final List<DayOfWeek> NOT_ORDERING_DAY = List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    /**
     * @param request
     * @return true -> merged, false -> inserted
     */
    @Transactional
    public void mergeOrder(OrderRequestDTO request, LocalDateTime startDate, LocalDateTime endDate) {
        OrderDTO orderDTO = request.getOrder();
        UserDTO userDTO = request.getUser();

        User user = userRepository.findByName(userDTO.getName())
                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, () -> new Dialog("잘못된 접근", "사용자가 등록되어 있지 않습니다.")));
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        List<Order> orders = new ArrayList<>();
        boolean isAdmin = user.getRole() == Role.ADMIN;
        for (int i = 0; i <= daysBetween; i++) {
            LocalDateTime dateTime = startDate.plusDays(i).toLocalDate().atStartOfDay();
            if (NOT_ORDERING_DAY.contains(dateTime.getDayOfWeek())) {
                continue;
            }
            Order order = orderRepository.findByUserAndCreatedAt(user, dateTime)
                    .map(o -> {
                        o.setPrice(orderDTO.getPrice());
                        o.setProductName(orderDTO.getProductName());
                        if (isAdmin) {
                            o.setPaid(true);
                            o.setPaidAt(dateTime);
                        }
                        return o;
                    }).orElseGet(() -> {
                        var builder = Order.builder()
                                .price(orderDTO.getPrice())
                                .user(user)
                                .createdAt(dateTime)
                                .productName(orderDTO.getProductName());
                        if (isAdmin) {
                            builder.isPaid(true).paidAt(dateTime);
                        }
                        return builder.build();
                    });
            orders.add(order);
        }
        orderRepository.saveAll(orders);
    }

    public List<OrderDTO> getOrdersByDate(LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orders = orderRepository.findAllByCreatedAtBetween(startDate, endDate, Sort.by("createdAt"));
        return orders.stream().map(order -> {
            OrderDTO orderDTO = EntityUtils.copyObject(order, OrderDTO.class);
            orderDTO.setUser(EntityUtils.copyObject(order.getUser(), UserDTO.class));
            return orderDTO;
        }).toList();
    }

    /**
     * @param orderNum
     * @return true -> paid false -> not paid yet
     */
    @Transactional
    public boolean togglePaidByOrderNum(long orderNum) {
        Order order = orderRepository.findById(orderNum).orElseThrow();
        order.setPaid(!order.isPaid());
        if (order.isPaid()) {
            order.setPaidAt(DateUtils.current());
        } else {
            order.setPaidAt(null);
        }
        orderRepository.save(order);
        return order.isPaid();
    }

    @Transactional
    public void setPaidAsTrueByUserName(String userName) {
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, () -> new Dialog("잘못된 접근", "사용자가 등록되어 있지 않습니다.")));
        LocalDate targetDate = LocalDate.now();
        LocalDateTime startDate = targetDate.atStartOfDay();
        LocalDateTime endDate = targetDate.atStartOfDay().plusDays(1).minusSeconds(1);
        Order order = orderRepository.findByUserAndCreatedAtBetween(user, startDate, endDate).orElseThrow();
        order.setPaid(true);
        order.setPaidAt(DateUtils.current());
        orderRepository.save(order);
    }

    public Map<String, Object> getUnpaidInformation() {
        Map<String, Integer> unpaidEachDate = new HashMap<>();
        Map<String, Integer> unpaidEachUser = new HashMap<>();
        List<Order> unpaidOrderList = orderRepository.findAllByisPaidAndProductNameIsNot(false, "먹지 않음");
        long totalUnpaidAmount = unpaidOrderList.stream()
                .mapToLong(Order::getPrice)
                .sum();

        unpaidOrderList.forEach(order -> {
            LocalDate localDate = order.getCreatedAt().toLocalDate();
            String userName = order.getUser().getName();
            int price = order.getPrice();

            unpaidEachDate.merge(localDate.toString(), price, Integer::sum);
            unpaidEachUser.merge(userName, price, Integer::sum);
        });
        Map<String, Object> result = new HashMap<>();
        result.put("totalAmount", totalUnpaidAmount);
        result.put("unpaidEachDate", unpaidEachDate);
        result.put("unpaidEachUser", unpaidEachUser);
        return result;
    }
    public List<UserDTO> getUnpaidUsers(){
        List<Order> unpaidOrderList = orderRepository.findAllByisPaidAndProductNameIsNot(false, "먹지 않음");
        return unpaidOrderList.stream().map(order -> EntityUtils.copyObject(order.getUser(), UserDTO.class)).toList();
    }
}
