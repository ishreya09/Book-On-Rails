package com.bookonrails.ooad.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookonrails.ooad.Interface.TicketFareDiscount;
import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Repository.TicketRepository;

@Service
public class TicketFareDiscountService implements TicketFareDiscount {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket addFareDiscount(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if (ticket != null) {
            double currentFare = ticket.getTotalAmount();
            double discountedFare = currentFare - 50.0;
            if (discountedFare < 0) {
                discountedFare = 0; // Ensure fare doesn't go negative
            }
            ticket.setFare(discountedFare);
            return ticketRepository.save(ticket);
        }
        return null;
    }
}
