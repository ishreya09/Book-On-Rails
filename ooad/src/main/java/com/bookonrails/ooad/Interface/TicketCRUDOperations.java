package com.bookonrails.ooad.Interface;

import java.sql.Date;
import java.util.List;

import com.bookonrails.ooad.Model.Passenger;
import com.bookonrails.ooad.Model.PaymentStatus;
import com.bookonrails.ooad.Model.Station;
import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Model.TicketStatus;
import com.bookonrails.ooad.Model.Train;
import com.bookonrails.ooad.Model.User;

public interface TicketCRUDOperations {
    List<Ticket> getAllTickets();
    Ticket getTicketById(Long id);
    Ticket saveTicket(Ticket ticket);
    Ticket updateTicket(Ticket ticket);
    void deleteTicket(Long id);
    List<Ticket> getTicketByUser(User user);

    // update
    Ticket updatePNR(Long ticketId, String newPNR);
    Ticket updateTrain(Long ticketId, Train newTrain);
    Ticket updateSource(Long ticketId, Station newSource);
    Ticket updateDestination(Long ticketId, Station newDestination);
    Ticket updatePassengers(Long ticketId, List<Passenger> newPassengers);
    Ticket updateTicketDate(Long ticketId, Date newDate);
    Ticket updateTicketStatus(Long ticketId, TicketStatus newStatus);
    Ticket updatePaymentStatus(Long ticketId, PaymentStatus newPaymentStatus);
}
