package com.example.ecommerceweb.controller;


import com.example.ecommerceweb.model.Reservation;
import com.example.ecommerceweb.model.ReservationDetail;
import com.example.ecommerceweb.repository.ReservationRepository;
import com.example.ecommerceweb.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    // usually in lower-case
    @RequestMapping(value = "/view-reservation")
    public String allReservation(Model model) {
        List<Reservation> reservations = reservationRepository.findAll();
        model.addAttribute("reservations", reservations);

        return "reservation-list";
    }

    @RequestMapping(value = "/view-reservation/{id}")
    public String detailReservation(@PathVariable(value = "id") Long id, Model model) {
        Reservation reservation = reservationService.findReservationById(id);
        List<ReservationDetail> resDetail = reservation.getReservationDetails();
        model.addAttribute("reservation", reservation);
        model.addAttribute("details", resDetail);

        return "reservation-detail";

    }
    
    @RequestMapping(value = "/delete/{id}")
    public String deleteReservation(@PathVariable(value = "id") Long id) {
        reservationRepository.deleteById(id);

        return "reservation-list";
    }

    @RequestMapping(value = "/approve/{id}")
    public String approveRepository(@PathVariable(value = "id") Long id) {
        Reservation reservation = reservationService.findReservationById(id);
        if (!reservation.isApprove()) {
            reservation.setApprove(true);
        } else {
            reservation.setApprove(false);
        }

        reservationRepository.save(reservation);

        return "redirect:/reservation/viewReservation";
    }







}
