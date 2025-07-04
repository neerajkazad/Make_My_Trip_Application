package com.nk.service;

import com.nk.request.Passenger;
import com.nk.response.Ticket;

public interface MakeMyTripService {

    public Ticket bookTicket(Passenger passenger);

    public Ticket getTicketByNum(Integer ticketNumber);

}
