package com.nk.controller;

import com.nk.request.Passenger;
import com.nk.response.Ticket;
import com.nk.service.MakeMyTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MakeMyTripController {

    @Autowired
    private MakeMyTripService service;

    @GetMapping("/get-ticket")
    public String getTicket(@RequestParam Integer ticketNum, Model model){
        Ticket ticketByNum = service.getTicketByNum(ticketNum);
        model.addAttribute("ticket", ticketByNum);
        return "ticket-form";
    }

    @GetMapping("/ticket")
    public String getTicketForm(Model model){
        model.addAttribute("ticket", new Ticket());
        return "ticket-form";
    }

    @PostMapping("/book-ticket")
    public String bookTicket(@ModelAttribute("passenger") Passenger passenger, Model model){
        Ticket ticket = service.bookTicket(passenger);
        model.addAttribute("msg", "Your ticket Booked with Id: "+ticket.getTicketNum());
        return "index";
    }

     @GetMapping("/")
     public String loadForm(Model model){
        model.addAttribute("passenger", new Passenger());
        return "index";
     }
}

