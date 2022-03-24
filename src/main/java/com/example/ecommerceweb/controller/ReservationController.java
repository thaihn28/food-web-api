package com.example.ecommerceweb.controller;


import com.example.ecommerceweb.dto.SearchObject;
import com.example.ecommerceweb.model.Reservation;
import com.example.ecommerceweb.model.ReservationDetail;
import com.example.ecommerceweb.repository.ReservationRepository;
import com.example.ecommerceweb.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    public String getAllReservationByFilter(@ModelAttribute("searchObject") @Valid SearchObject searchObject,
                                            BindingResult result,
                                            Model model) throws ParseException {

        if (searchObject == null) {
            searchObject = new SearchObject();
            searchObject.setStatus(false);
            searchObject.setOrder("sendDESC");
        }
        SimpleDateFormat spm = new SimpleDateFormat("yyyy-MM-dd");
        if (searchObject.getFromDate() != null && searchObject.getToDate() != null) {
            if (!searchObject.getFromDate().isEmpty() && !searchObject.getToDate().isEmpty()) {
                Date fromDate = spm.parse(searchObject.getFromDate());
                Date toDate = spm.parse(searchObject.getToDate());
                if (fromDate.after(toDate)) {
                    result.rejectValue("fromDate", "fromDate.error", "Ngày bắt đầu không được lớn hơn ngày kết thúc");
                }
            }
        }

        List<Reservation> reservations = reservationService.findReservationByNameAndDateAndStatus(searchObject, 0, 0);

        //session.setAttribute("reservations", reservations);
        Collections.sort(reservations);
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
    public String deleteReservation(@PathVariable(value = "id") Long id/*, HttpSession session, HttpServletRequest req*/) {
        reservationRepository.deleteById(id);


        return "redirect:/reservation/view-reservation/";
    }

    @RequestMapping(value = "/approve/{id}")
    public String approveReservation(@PathVariable(value = "id") Long id,
                                    HttpServletRequest req,
                                    Model model) {
        Reservation reservation = reservationService.findReservationById(id);
        if (!reservation.isApprove()) {
            reservation.setApprove(true);
        } else {
            reservation.setApprove(false);
        }

        reservationRepository.save(reservation);

        return "redirect:/reservation/view-reservation";
    }

    @RequestMapping(value = "/confirm/{id}")
    public String confirmReservation(@PathVariable(value = "id") Long id, BindingResult result) {
        Reservation reservation = reservationService.findReservationById(id);
        if (reservation.isApprove() == false) {
            //result.reject("Reservation must be approved before confirm");
//            ObjectError error = new ObjectError("globalError", "Reservation must be approved before confirm");
//            rdeesult.addError(error);
//            return "redirect:/reservation/view-reservation";
        }
        reservation.setComplete(true);
        reservationRepository.save(reservation);

        return "redirect:/reservation/view-reservation";
    }

    @RequestMapping("/refresh")
    public String refresh() {
        return "redirect:/reservation/view-reservation";
    }









}
