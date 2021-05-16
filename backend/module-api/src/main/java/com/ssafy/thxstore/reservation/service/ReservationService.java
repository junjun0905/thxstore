package com.ssafy.thxstore.reservation.service;

import com.ssafy.thxstore.reservation.dto.ReservationDto;
import com.ssafy.thxstore.reservation.dto.ReservationGroupDto;
import com.ssafy.thxstore.reservation.dto.StatusRequest;

import java.util.List;

public interface ReservationService {
    void addReservation(String email,ReservationDto reservationList);
    List<ReservationDto> getReservation(String email,String type);
    void deleteReservation(String email,Long Id,String type);
    void statusUpdate(String email, StatusRequest status);
//    void addOrder(Long memberId);
//    List<ReservationDto> getOrder(Long memberId);
}
