package com.bookonrails.ooad.Interface;

import com.bookonrails.ooad.Model.Ticket;

public interface TicketAllocationOperations {
    Ticket allocateSeats(Ticket ticket);
    void cancelTicket(Ticket ticket);
}
