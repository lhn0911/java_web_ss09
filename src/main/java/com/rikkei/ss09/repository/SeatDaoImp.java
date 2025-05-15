package com.rikkei.ss09.repository;

import com.rikkei.ss09.model.Seat;
import com.rikkei.ss09.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SeatDaoImp implements SeatDao {

    @Override
    public List<Seat> findAllByScreenRoom(Long screenRoomId) {
        List<Seat> seats = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_find_Seats_By_ScreenRoomId(?)}");
            callSt.setLong(1, screenRoomId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Seat seat = new Seat();
                seat.setId(rs.getLong("id"));
                seat.setScreenRoomId(rs.getLong("screen_room_id"));
                seat.setPrice(rs.getDouble("price"));
                seat.setStatus(rs.getString("status"));
                seats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return seats;
    }

    @Override
    public List<Seat> findSeatsByIds(List<Long> seatIds) {
        List<Seat> seats = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_find_Seats_By_ScreenRoomId(?)}");
            callSt.setLong(1, screenRoomId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Seat seat = new Seat();
                seat.setId(rs.getLong("id"));
                seat.setScreenRoomId(rs.getLong("screen_room_id"));
                seat.setPrice(rs.getDouble("price"));
                seat.setStatus(rs.getString("status"));
                seats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return seats;

    }
}
