package com.bookonrails.ooad.Service;


import com.bookonrails.ooad.Model.Ticket;

// OCP
public class TatkalTicket extends TicketService {

    public Ticket addFareDiscount(Long ticketId){
        Ticket ticket =getTicketById(ticketId);
        if (ticket != null) {
            double currentFare = ticket.getTotalAmount();
            double discountedFare = currentFare + 50.0; // pay 50 extra for tatkal tickets
            if (discountedFare < 0) {
                discountedFare = 0; // Ensure fare doesn't go negative
            }
            ticket.setFare(discountedFare);
            return saveTicket(ticket);
        }
        return null;
    }
    
}
