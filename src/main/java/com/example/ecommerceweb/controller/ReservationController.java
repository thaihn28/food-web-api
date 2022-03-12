package com.example.ecommerceweb.controller;


import com.example.ecommerceweb.dto.SearchObject;
import com.example.ecommerceweb.model.Reservation;
import com.example.ecommerceweb.model.ReservationDetail;
import com.example.ecommerceweb.repository.ReservationRepository;
import com.example.ecommerceweb.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getAllReservationByFilter(/*@RequestParam(value = "status", required = false) Boolean status,
                                            @RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "from", required = false) String from,
                                            @RequestParam(value = "to", required = false) String to,*/
            @ModelAttribute("searchObject") SearchObject searchObject,
                                            Model model) throws ParseException {

        if (searchObject == null) {
            searchObject = new SearchObject();
        }
        //System.out.println(searchObject.getName() + "/");
        /*searchObject.setName(name);
        searchObject.setFromDate(from);
        searchObject.setToDate(to);
        searchObject.setStatus(status);
*/
        List<Reservation> reservations = reservationService.findReservationByNameAndDateAndStatus(searchObject, 0, 0);

        model.addAttribute("reservations", reservations);
        model.addAttribute("searchObject", searchObject);

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
    public String approveRepository(@PathVariable(value = "id") Long id, @ModelAttribute("searchObject") SearchObject searchObject,
                                    Model model) {
        Reservation reservation = reservationService.findReservationById(id);
        if (!reservation.isApprove()) {
            reservation.setApprove(true);
        } else {
            reservation.setApprove(false);
        }

        reservationRepository.save(reservation);
        model.addAttribute("searchObject", searchObject);

        return "redirect:/reservation/view-reservation";
    }









}
