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
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public String getAllReservationByFilter(@RequestParam(value = "approve", required = false) Boolean status,
                                            @RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "from", required = false) String from,
                                            @RequestParam(value = "to", required = false) String to,
                                            Model model) throws ParseException {
        SimpleDateFormat spm = new SimpleDateFormat("yyyy-MM-dd");
        List<Reservation> reservations;

        reservations = reservationService.findReservationByNameAndDateAndStatus("B", "2021-03-11",
                "2021-03-21", null);
        //List<Reservation> reservations1 = reservationRepository.findAllByApprove(true);
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

        return "redirect:/reservation/view-reservation/";
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

        return "redirect:/reservation/view-reservation";
    }









}
