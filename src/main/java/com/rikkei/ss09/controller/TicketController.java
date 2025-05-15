package com.rikkei.ss09.controller;

import com.rikkei.ss09.model.Schedule;
import com.rikkei.ss09.model.Seat;
import com.rikkei.ss09.model.Ticket;
import com.rikkei.ss09.service.ScheduleService;
import com.rikkei.ss09.service.SeatService;
import com.rikkei.ss09.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller

public class TicketController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/book")
    public String bookTicket(@RequestParam("scheduleId") Long scheduleId, Model model) {
        Schedule schedule = scheduleService.findById(scheduleId);
        List<Seat> seats = seatService.findAllByScreenRoom(schedule.getScreenRoomId());

        model.addAttribute("schedule", schedule);
        model.addAttribute("seats", seats);
        return "bookTicket";
    }

    @PostMapping("/submitBooking")
    public String submitBooking(@RequestParam("scheduleId") Long scheduleId,
                                @RequestParam("customerId") Long customerId,
                                @RequestParam("seatIds") List<Long> seatIds,
                                Model model) {

        List<Seat> selectedSeats = seatService.findSeatsByIds(seatIds);
        double totalMoney = selectedSeats.stream().mapToDouble(Seat::getPrice).sum();

        Ticket ticket = new Ticket();
        ticket.setCustomerId(customerId);
        ticket.setScheduleId(scheduleId);
        ticket.setListSeat(selectedSeats);
        ticket.setTotalMoney(totalMoney);

        ticketService.addTicket(ticket);

        model.addAttribute("message", "Đặt vé thành công! Tổng tiền: " + totalMoney);
        return "bookingResult";
    }
}
