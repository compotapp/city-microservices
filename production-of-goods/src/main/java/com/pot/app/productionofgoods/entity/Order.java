package com.pot.app.productionofgoods.entity;

import com.pot.app.productionofgoods.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Order extends NumberedEntity {

    @Column(nullable = false)
    private String owner;

    @Enumerated(STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = ALL, orphanRemoval = true, fetch = LAZY)
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();
}

//mappedBy = "deliveryRequest" Указывает, что владельцем связи является поле deliveryRequest в сущности DeliveryRequestItem. Без mappedBy Hibernate создал бы дополнительную таблицу-связку (join table)

//CascadeType.ALL = {
//CascadeType.PERSIST,   // при сохранении родителя сохраняются и дети
//CascadeType.MERGE,     // при обновлении родителя обновляются и дети
//CascadeType.REMOVE,    // при удалении родителя удаляются и дети
//CascadeType.REFRESH,   // при обновлении данных из БД обновляются и дети
//CascadeType.DETACH     // при отсоединении родителя отсоединяются и дети
//}

//orphanRemoval = true Если удалить элемент из коллекции items, он автоматически удалится из БД
//DeliveryRequest request = entityManager.find(DeliveryRequest.class, 1L);
// В БД есть items с id 1, 2, 3
// Удаляем один элемент из коллекции
// request.getItems().removeIf(item -> item.getId().equals(2L));
// При сохранении (или автоматически при flush) Hibernate выполнит:
// DELETE FROM delivery_request_items WHERE id = 2
//CascadeType.REMOVE - удаляются все дети при удалении родителя
//orphanRemoval = true - удаляются дети, которые были отсоединены от родителя

//@Builder.Default Указывает Lombok, что при использовании паттерна Builder нужно использовать указанное значение по умолчанию в нашем случае new ArrayList<>()
