package com.bobtime.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserMessage implements ResponseMessage {
    CONFLICT("중복된 이름입니다.");
    private final String value;
}
