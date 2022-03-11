package com.example.ecommerceweb.service;

import com.example.ecommerceweb.model.Reservation;
import com.example.ecommerceweb.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    public Reservation findReservationById(Long id) {
       return reservationRepository.findById(id).get();
    }

    public List<Reservation> findResByApprove(boolean status) {
        List<Reservation> allRes = reservationRepository.findAll();
        List<Reservation> returnRes = new ArrayList<>();
        for (int i =0; i < allRes.size(); i++) {
            if (allRes.get(i).isApprove() == status) {
                returnRes.add(allRes.get(i));
            }
        }
        return  returnRes;
    }

    public List<Reservation> findResByApprove(boolean status, List<Reservation> allRes) {
        //List<Reservation> allRes = reservationRepository.findAll();
        List<Reservation> returnRes = new ArrayList<>();
        for (int i =0; i < allRes.size(); i++) {
            if (allRes.get(i).isApprove() == status) {
                returnRes.add(allRes.get(i));
            }
        }
        return  returnRes;
    }
    public List<Reservation> findReservationByNameAndDateAndStatus(String name, String from, String to, Boolean status) throws ParseException {
        //Ưu tiên xếp theo tên
        //Ưu tiên xếp theo thời gian
        //Ưu tiên xếp theo status
        List<Reservation> allRes = reservationRepository.findAll();
        if (name == null || name.length() == 0 && status == null) {

            return findReservationByDate(allRes, from, to);

        }
        if (status == null) {
            allRes = reservationRepository.findAllByClientNameContaining(name);
            return findReservationByDate(allRes, from, to);
        }
        if (name == null || name.length() == 0) {
            allRes = findResByApprove(status);
            return findReservationByDate(allRes, from, to);
        }

        if (name != null || name.length() != 0 && status != null) {

            allRes = reservationRepository.findAllByClientNameContaining(name);
            allRes = findResByApprove(status, allRes);
            return findReservationByDate(allRes, from, to);
        }

        return allRes;
    }

    public List<Reservation> findReservationByDate(List<Reservation> reservations, String from, String to) throws ParseException {

        List<Reservation> returnRes = new ArrayList<>();
        SimpleDateFormat spm = new SimpleDateFormat("yyyy-MM-dd");


        if (from == null && to == null) {
            return reservations;
        }

        if (from == null) {
            return reservations;
        }

        Date from1 = spm.parse(from);
        if (to == null) {
            Date today = new Date();
            for (int i =0; i < reservations.size(); i++) {
                Reservation res = reservations.get(i);
                if (res.getDate().after(from1) && res.getDate().before(today)) {
                    returnRes.add(res);
                }
            }
            return returnRes;
        }
        Date to1 = spm.parse(to);
        for (int i =0; i < reservations.size(); i++) {
            Reservation res = reservations.get(i);
            if (res.getDate().after(from1) && res.getDate().before(to1)) {
                if (!res.getDate().equals(from1))
                 returnRes.add(res);
            }
        }
        return returnRes;
    }

}
