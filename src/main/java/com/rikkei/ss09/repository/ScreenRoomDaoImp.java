package com.rikkei.ss09.repository;

import com.rikkei.ss09.model.ScreenRoom;
import com.rikkei.ss09.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScreenRoomDaoImp implements ScreenRoomDao {
    @Override
    public List<ScreenRoom> findAll() {
        List<ScreenRoom> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_All_ScreenRooms()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                list.add(new ScreenRoom(
                        rs.getLong("id"),
                        rs.getString("screen_room_name"),
                        rs.getInt("total_seat")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return list;
    }
}