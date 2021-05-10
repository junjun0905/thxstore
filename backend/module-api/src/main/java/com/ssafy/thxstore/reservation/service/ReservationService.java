package com.ssafy.thxstore.reservation.service;

import com.ssafy.thxstore.reservation.dto.CartDto;
import com.ssafy.thxstore.reservation.dto.OrderRequest;

import java.util.List;

public interface ReservationService {
    void addCart(List<CartDto> cartList);
    List<CartDto> getCart(Long memberId);
    void addOrder(OrderRequest orderRequest);

//    List<ReservationDto> getOrder(Long memberId);
}
