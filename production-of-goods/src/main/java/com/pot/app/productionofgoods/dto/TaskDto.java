package com.pot.app.productionofgoods.dto;

import com.pot.app.productionofgoods.enums.TaskStatus;
import com.pot.app.productionofgoods.enums.TaskType;

public record TaskDto(
        String number,
        long productid,
        long orderId,
        Integer quantity,
        TaskType type,
        TaskStatus status
) {
}
