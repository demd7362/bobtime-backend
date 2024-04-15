package com.bobtime.dto.request;

import com.bobtime.common.utils.DateUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {

    @Builder.Default
    private LocalDateTime createdAt = DateUtils.current();
}
