package com.rikkei.ss09.service;

import com.rikkei.ss09.model.Ticket;
import com.rikkei.ss09.repository.TicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketDao ticketDao;
    @Override
    public void addTicket(Ticket ticket) {

        ticketDao.addTicket(ticket);
    }
}
