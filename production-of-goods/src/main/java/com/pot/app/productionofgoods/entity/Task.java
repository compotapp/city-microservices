package com.pot.app.productionofgoods.entity;

import com.pot.app.productionofgoods.enums.TaskStatus;
import com.pot.app.productionofgoods.enums.TaskType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "tasks")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Task extends NumberedEntity {

    @Column(nullable = false)
    long productId;

    @Column()
    long orderId;

    @Column(nullable = false)
    int quantity;

    @Column(nullable = false)
    @Builder.Default
    int completeQuantity = 0;

    @Enumerated(STRING)
    @Column(nullable = false)
    TaskType type;

    @Enumerated(STRING)
    @Column(nullable = false)
    TaskStatus status;
}
