package com.bobtime.controller;

import com.bobtime.common.enums.OrderMessage;
import com.bobtime.dto.model.OrderDTO;
import com.bobtime.dto.request.OrderRequestDTO;
import com.bobtime.dto.response.ResponseDTO;
import com.bobtime.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createOrder(@RequestBody OrderRequestDTO request){
        orderService.createOrder(request);
        return ResponseDTO.entityBuilder()
                .message(OrderMessage.ORDERED)
                .httpStatus(HttpStatus.OK)
                .build();
    }
    @GetMapping("/orders")
    public ResponseEntity<ResponseDTO> getOrders(@RequestParam("date") @DateTimeFormat LocalDateTime dateTime){
        List<OrderDTO> orders = orderService.getOrdersByDate(dateTime);
        return ResponseDTO.entityBuilder()
                .message(OrderMessage.GET_SUCCESS)
                .httpStatus(HttpStatus.OK)
                .data(orders)
                .build();
    }
    @PatchMapping("/toggle-paid/{orderNum}")
    public ResponseEntity<ResponseDTO> getOrders(@PathVariable long orderNum){
        orderService.togglePaid(orderNum);
        return ResponseDTO.entityBuilder()
                .message(() -> "성공했습니다.")
                .httpStatus(HttpStatus.OK)
                .build();
    }

}
