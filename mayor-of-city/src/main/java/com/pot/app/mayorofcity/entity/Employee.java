package com.pot.app.mayorofcity.entity;

import com.pot.app.core.dto.mayor.of.city.enums.EmployeeStatus;
import com.pot.app.core.dto.mayor.of.city.enums.EmployeeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "employees")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String number;

    @Column(nullable = false)
    @Builder.Default
    int level = 2;

    @Column(nullable = false)
    int exp;

    @Enumerated(STRING)
    @Column(nullable = false)
    EmployeeType type;

    @Enumerated(STRING)
    @Column(nullable = false)
    EmployeeStatus status;
}
