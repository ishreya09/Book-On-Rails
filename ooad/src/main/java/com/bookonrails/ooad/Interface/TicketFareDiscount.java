package com.bookonrails.ooad.Interface;

import com.bookonrails.ooad.Model.Ticket;

//  for ocp
public interface TicketFareDiscount {
    Ticket addFareDiscount(Long ticketId,double discount);
}


