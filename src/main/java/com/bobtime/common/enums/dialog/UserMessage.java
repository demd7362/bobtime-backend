package com.bobtime.common.enums.dialog;

import com.bobtime.common.enums.dialog.DialogMessage;
import com.bobtime.common.model.Dialog;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserMessage implements DialogMessage {
    ROLE_UPDATE_SUCCESS(new Dialog("변경 성공","변경에 성공하였습니다.")),
    CREATE_SUCCESS(new Dialog("등록 성공","등록에 성공하였습니다."));
    private final Dialog dialog;
}
