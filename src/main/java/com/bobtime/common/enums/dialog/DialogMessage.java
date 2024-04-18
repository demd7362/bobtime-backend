package com.bobtime.common.enums.dialog;

import com.bobtime.common.model.Dialog;
import com.fasterxml.jackson.annotation.JsonValue;


@FunctionalInterface
public interface DialogMessage {
    @JsonValue
    Dialog getDialog();
}
