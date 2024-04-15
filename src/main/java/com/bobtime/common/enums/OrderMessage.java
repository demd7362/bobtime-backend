package com.bobtime.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Getter
public enum OrderMessage implements ResponseMessage {
    ORDERED("주문 신청이 완료되었습니다."),
    CANCELLED("주문 신청이 취소되었습니다."),
    CLOSED("주문이 마감되었습니다."),
    GET_SUCCESS("주문 조회에 성공했습니다.");



    private final String value;

}
