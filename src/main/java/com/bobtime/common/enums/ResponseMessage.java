package com.bobtime.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

@FunctionalInterface
public interface ResponseMessage {
    @JsonValue
    String getValue();
}
