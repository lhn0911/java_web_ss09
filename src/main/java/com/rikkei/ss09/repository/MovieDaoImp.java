package com.rikkei.ss09.repository;

import com.rikkei.ss09.model.Customer;
import com.rikkei.ss09.model.Movie;
import com.rikkei.ss09.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieDaoImp implements MovieDao {


    @Override
    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        Customer customer = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_All_Movie()}");
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("director"),
                        rs.getString("genre"),
                        rs.getString("description"),
                        rs.getInt("duration"),
                        rs.getString("language")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đăng nhập: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return movies;
    }

    @Override
    public Movie findById(Long id) {
        Movie movie = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_ById(?)}");
            callSt.setLong(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                movie = new Movie(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("director"),
                        rs.getString("genre"),
                        rs.getString("description"),
                        rs.getInt("duration"),
                        rs.getString("language")
                );
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy xuất phim theo ID: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return movie;
    }
}
