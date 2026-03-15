package com.pot.app.productionofgoods.entity;

import com.pot.app.productionofgoods.enums.EmployeeStatus;
import com.pot.app.productionofgoods.enums.EmployeeType;
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
public class Employee extends NumberedEntity {

    @Column(nullable = false)
    @Builder.Default
    int level = 2;

    @Column(nullable = false)
    int exp;

    @Column(nullable = false)
    int points;

    @Enumerated(STRING)
    @Column(nullable = false)
    EmployeeType type;

    @Enumerated(STRING)
    @Column(nullable = false)
    EmployeeStatus status;
}
