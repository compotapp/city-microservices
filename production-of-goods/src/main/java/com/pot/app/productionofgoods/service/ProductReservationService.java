package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.OrderDto;

public interface ProductReservationService {

    OrderDto reservOrder(OrderDto dto);

    OrderDto canselReserveOrder(String orderNumber);

    OrderDto confirmReserveOrder(String orderNumber);
}
