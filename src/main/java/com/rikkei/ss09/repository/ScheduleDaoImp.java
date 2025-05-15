package com.rikkei.ss09.repository;

import com.rikkei.ss09.model.Schedule;
import com.rikkei.ss09.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleDaoImp implements ScheduleDao{
    @Override
    public List<Schedule> findAllScheduleByMovie(Long movieId) {
        List<Schedule> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_Schedules_By_MovieId(?)}");
            callSt.setLong(1, movieId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                list.add(new Schedule(
                        rs.getLong("id"),
                        rs.getString("movie_title"),
                        rs.getTimestamp("show_time"),
                        rs.getLong("screen_room_id"),
                        rs.getInt("available_seats"),
                        rs.getString("format")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return list;
    }

    @Override
    public Schedule findById(Long id) {
        Schedule schedule = null;
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_schedule_byId(?)}");
            callSt.setLong(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                schedule = new Schedule();
                schedule.setId(rs.getLong("id"));
                schedule.setShowTime(rs.getTimestamp("show_time"));
                schedule.setScreenRoomId(rs.getLong("screen_room_idroom_id"));
                schedule.setAvailableSeats(rs.getInt("available_seats"));
                schedule.setFormat(rs.getString("format"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return schedule;
    }
}
