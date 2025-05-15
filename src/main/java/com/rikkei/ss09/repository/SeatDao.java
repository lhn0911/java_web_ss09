package com.rikkei.ss09.repository;

import com.rikkei.ss09.model.Seat;

import java.util.List;

public interface SeatDao {
    List<Seat> findAllByScreenRoom(Long screenRoomId);
    List<Seat> findSeatsByIds(List<Long> seatIds);
}
