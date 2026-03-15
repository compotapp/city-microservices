package com.pot.app.productionofnumbers.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "number_sequences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NumberSequence {

    @Id
    @Enumerated(STRING)
    private SequenceType type;

    @Column(name = "last_number", nullable = false)
    private int lastNumber;

    public NumberSequence(SequenceType type) {
        this.type = type;
        this.lastNumber = 0;
    }

    public List<Integer> getNextNumbers(int count) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            numbers.add(lastNumber + i);
        }
        lastNumber = lastNumber + count;
        return numbers;
    }

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

        public static SequenceType valueOfPrefix(String prefix) {
            return Arrays.stream(values())
                    .filter(sequenceType -> sequenceType.prefix.equalsIgnoreCase(prefix))
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
        }
    }
}
