package com.example.ecommerceweb.repository;

import com.example.ecommerceweb.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    //List<Reservation> findResByApprove(boolean approve);
    List<Reservation> findAllByPhoneNumberContaining(String phoneNumber);
    List<Reservation> findAllByClientNameContaining(String clientName);

}
