package com.pot.app.productionofgoods.controller;

import com.pot.app.productionofgoods.dto.OrderDto;
import com.pot.app.productionofgoods.service.ProductReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final ProductReservationService service;

    @PostMapping("/reservation")
    public OrderDto reservation(@RequestBody OrderDto dto) {
        return service.reservOrder(dto);
    }

    @PostMapping("/cancel-reservation")
    public OrderDto canselReservation(@RequestBody OrderDto dto) {
        return service.canselReserveOrder(dto.number());
    }

    @PostMapping("/confirm-reservation")
    public OrderDto confirmReservation(@RequestBody OrderDto dto) {
        return service.confirmReserveOrder(dto.number());
    }
}
