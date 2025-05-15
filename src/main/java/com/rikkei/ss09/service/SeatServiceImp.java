package com.rikkei.ss09.service;

import com.rikkei.ss09.model.Seat;
import com.rikkei.ss09.repository.SeatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeatServiceImp implements SeatService {
    @Autowired
    private SeatDao seatDao;
    @Override
    public List<Seat> findAllByScreenRoom(Long screenRoomId) {
        return seatDao.findAllByScreenRoom(screenRoomId);
    }

    @Override
    public List<Seat> findSeatsByIds(List<Long> seatIds) {
        return List.of();
    }
}
