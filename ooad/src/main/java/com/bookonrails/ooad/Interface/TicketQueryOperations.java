package com.bookonrails.ooad.Interface;

import com.bookonrails.ooad.Model.Ticket;

import java.util.List;

public interface TicketQueryOperations {
    List<Ticket> getCancelledTickets();
    List<Ticket> getConfirmedTickets();
    List<Ticket> getWaitingListTickets();
}
