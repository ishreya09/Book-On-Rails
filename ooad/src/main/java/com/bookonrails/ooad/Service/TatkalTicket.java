package com.bookonrails.ooad.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Repository.TicketRepository;

public class TatkalTicket extends TicketAbstractService {

    @Autowired
    private TicketRepository ticketRepository;
    
    TatkalTicket(){
        super();
    }

    @Override
    public Ticket addFareDiscount(Long ticketId,double discount) {
        // If ticket is Tatkal, we give discount for the ticket price and add tax and a double the base fare
        Ticket t= ticketRepository.findById(ticketId).orElse(null);
        if(t!=null) {
            double currentFare = t.getTotalAmount();
            double doubleFare = 2* currentFare;
            double discountedFare = doubleFare - discount;
            if (discountedFare < 0) {
                discountedFare = 0; // Ensure fare doesn't go negative
            }
            // t.setFare(discountedFare);
            t.setFare(discountedFare + (discountedFare * 0.18)); // Add 18% tax
            return ticketRepository.save(t);
        }
        return null;
    }
    
    
}
