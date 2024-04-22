package com.bookonrails.ooad.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Repository.TicketRepository;

@Service
public class GeneralTicket extends TicketAbstractService {

    @Autowired
    private TicketRepository ticketRepository;
    
    GeneralTicket(){
        super();
    }

    @Override
    public Ticket addFareDiscount(Long ticketId,double discount) {
        // If ticket is General, we give discount for the ticket price and add tax
        Ticket t= ticketRepository.findById(ticketId).orElse(null);
        if(t!=null) {
            double currentFare = t.getTotalAmount();
            double discountedFare = currentFare - discount;
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
