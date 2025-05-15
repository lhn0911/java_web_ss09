package com.rikkei.ss09.repository;

import com.rikkei.ss09.model.Ticket;
import com.rikkei.ss09.model.Seat;
import com.rikkei.ss09.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class TicketDaoImp implements TicketDao {

    @Override
    public void addTicket(Ticket ticket) {
        Connection conn = null;
        CallableStatement callTicket = null;
        CallableStatement callSeat = null;
        CallableStatement callLink = null;
        CallableStatement callUpdateSeat = null;

        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);

            // Gọi procedure thêm vé
            callTicket = conn.prepareCall("{call add_ticket(?, ?, ?, ?)}");
            callTicket.setLong(1, ticket.getCustomerId());
            callTicket.setLong(2, ticket.getScheduleId());
            callTicket.setDouble(3, ticket.getTotalMoney());
            callTicket.registerOutParameter(4, Types.BIGINT);
            callTicket.execute();
            Long ticketId = callTicket.getLong(4);

            // Set ticket ID
            ticket.setId(ticketId);

            List<Seat> seats = ticket.getListSeat();
            for (Seat seat : seats) {
                // Gọi procedure thêm ticket_seat
                callLink = conn.prepareCall("{call add_ticket_seat(?, ?)}");
                callLink.setLong(1, ticketId);
                callLink.setLong(2, seat.getId());
                callLink.execute();

                // Gọi procedure cập nhật trạng thái ghế
                callSeat = conn.prepareCall("{call update_seat_status(?, ?)}");
                callSeat.setLong(1, seat.getId());
                callSeat.setString(2, "booked");
                callSeat.execute();
            }

            // Gọi procedure cập nhật số lượng ghế trống
            callUpdateSeat = conn.prepareCall("{call decrease_available_seats(?, ?)}");
            callUpdateSeat.setLong(1, ticket.getScheduleId());
            callUpdateSeat.setInt(2, seats.size());
            callUpdateSeat.execute();

            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callTicket);
            ConnectionDB.closeConnection(null, callSeat);
            ConnectionDB.closeConnection(null, callLink);
            ConnectionDB.closeConnection(null, callUpdateSeat);
        }
    }
}