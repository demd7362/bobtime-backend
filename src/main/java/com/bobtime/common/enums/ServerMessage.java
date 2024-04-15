package com.bobtime.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ServerMessage implements ResponseMessage {
    INTERNAL_SERVER_ERROR("서버에서 에러가 발생하였습니다.");

    private final String value;
}

