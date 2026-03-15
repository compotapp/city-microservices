package com.pot.app.productionofgoods.entity;

import com.pot.app.productionofgoods.enums.OrderItemStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "order_items")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends BaseEntity {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Enumerated(STRING)
    @Column(nullable = false)
    private OrderItemStatus status;

    // Можно добавить цену, скидку и т.д.
    // private BigDecimal price;
    // private BigDecimal discount;
}
