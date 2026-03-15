package com.pot.app.core.dto.production.of.numbers;

import lombok.AllArgsConstructor;
import lombok.Getter;

public record NumberSequenceRequest(
        SequenceType type,
        int count
) {

    @Getter
    @AllArgsConstructor
    public enum SequenceType {
        EMPLOYEE("EMP"),
        ORDER("ORD"),
        OWNER("OWN"),
        PRODUCT("PRD"),
        TASK("TSK"),
        TEST("TEST");

        private final String prefix;
    }
}
