package com.bobtime.common.enums.dialog;

import com.bobtime.common.enums.dialog.DialogMessage;
import com.bobtime.common.model.Dialog;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ServerMessage implements DialogMessage {
    INTERNAL_SERVER_ERROR(new Dialog("서버 에러","서버에서 에러가 발생하였습니다."));
    private final Dialog dialog;
}

