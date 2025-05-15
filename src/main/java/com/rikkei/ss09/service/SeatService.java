package com.rikkei.ss09.service;

import com.rikkei.ss09.model.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> findAllByScreenRoom(Long screenRoomId);
    List<Seat> findSeatsByIds(List<Long> seatIds);
}
