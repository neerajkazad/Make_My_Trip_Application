package com.nk.service.impl;

import com.nk.request.Passenger;
import com.nk.response.Ticket;
import com.nk.service.MakeMyTripService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MakeMyTripServiceImpl implements MakeMyTripService {

    private String BOOK_TICKET_URL = "http://localhost:8081/ticket";

    private String GET_TICKET_URL = "http://localhost:8081/ticket/{ticketNum}";

    @Override
    public Ticket bookTicket(Passenger passenger) {

        // get the instance of webclient (impl class)
        WebClient webClient = WebClient.create();

        // send POST request with passenger data
        // and map response to Ticket Obj

        Ticket ticket = webClient.post()
                                 .uri(BOOK_TICKET_URL)
                                 .bodyValue(passenger)
                                 .retrieve()
                                 .bodyToMono(Ticket.class)
                                 .block(); // sync call

        return ticket;
        /*RestTemplate rt = new RestTemplate();
        ResponseEntity<Ticket> responseEntity = rt.postForEntity(BOOK_TICKET_URL, passenger, Ticket.class);

        Ticket ticket = responseEntity.getBody();
         return null;
        */

    }

    @Override
    public Ticket getTicketByNum(Integer ticketNumber) {

        // get the instance of webclient (impl class)
        WebClient webClient = WebClient.create();

        // send get request and map response to Ticket Obj

        Ticket ticket = webClient.get()
                                 .uri(GET_TICKET_URL, ticketNumber)
                                 .retrieve()
                                 .bodyToMono(Ticket.class)
                                 .block(); // sync call

        return ticket;


        /*RestTemplate rt = new RestTemplate();
        ResponseEntity<Ticket> responseEntity = rt.getForEntity(GET_TICKET_URL, Ticket.class, ticketNumber);

        Ticket ticket = responseEntity.getBody();
        return null;
        */
    }
}
