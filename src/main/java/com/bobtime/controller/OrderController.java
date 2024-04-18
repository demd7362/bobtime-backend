package com.bobtime.controller;

import com.bobtime.common.enums.dialog.DialogMessage;
import com.bobtime.common.enums.dialog.OrderMessage;
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

    @PostMapping("/merge")
    public ResponseEntity<ResponseDTO> mergeOrder(@RequestBody OrderRequestDTO request){
        boolean isMerged = orderService.mergeOrder(request);
        OrderMessage message = isMerged ? OrderMessage.MERGED : OrderMessage.INSERTED;
        return ResponseDTO.entityBuilder()
                .message(message)
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
    public ResponseEntity<ResponseDTO> togglePaid(@PathVariable long orderNum){
        boolean isPaid = orderService.togglePaidByOrderNum(orderNum);
        DialogMessage message = isPaid ? OrderMessage.PAID : OrderMessage.NOT_PAID;
        return ResponseDTO.entityBuilder()
                .message(message)
                .httpStatus(HttpStatus.OK)
                .build();
    }
    @PatchMapping("/paid/{userName}")
    public ResponseEntity<ResponseDTO> setPaidAsTrueByUserName(@PathVariable String userName){
        orderService.setPaidAsTrueByUserName(userName);
        return ResponseDTO.entityBuilder()
                .message(OrderMessage.PAID)
                .httpStatus(HttpStatus.OK)
                .build();
    }
    @GetMapping("/unpaid-info")
    public ResponseEntity<ResponseDTO> getUnpaidInformation(){
        var data = orderService.getUnpaidInformation();
        return ResponseDTO.entityBuilder()
                .message(OrderMessage.PAID)
                .data(data)
                .httpStatus(HttpStatus.OK)
                .build();
    }

}
