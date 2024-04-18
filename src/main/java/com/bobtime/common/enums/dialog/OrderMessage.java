package com.bobtime.common.enums.dialog;

import com.bobtime.common.enums.dialog.DialogMessage;
import com.bobtime.common.model.Dialog;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderMessage implements DialogMessage {
    INSERTED(new Dialog("주문 성공","주문 신청이 완료되었습니다.")),
    MERGED(new Dialog("주문 변경 성공","주문 변경이 완료되었습니다.")),
    CANCELLED(new Dialog("주문 취소","주문 신청이 취소되었습니다.")),
    CLOSED(new Dialog("주문 마감","주문이 마감되었습니다.")),
    GET_SUCCESS(new Dialog("조회 성공","주문 조회에 성공했습니다.")),
    PAID(new Dialog("변경 성공","비용 납부 완료 처리되었습니다.")),
    NOT_PAID(new Dialog("변경 성공","비용 납부 미완료 처리되었습니다."));

    private final Dialog dialog;
}
