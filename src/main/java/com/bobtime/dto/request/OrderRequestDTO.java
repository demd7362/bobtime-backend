package com.bobtime.dto.request;

import com.bobtime.dto.model.OrderDTO;
import com.bobtime.dto.model.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO extends RequestDTO {
    private OrderDTO order;
    private UserDTO user;
}
