package com.example.ecommerceweb.service;

import com.example.ecommerceweb.model.Reservation;
import com.example.ecommerceweb.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    public Reservation findReservationById(Long id) {
       return reservationRepository.findById(id).get();
    }
}
