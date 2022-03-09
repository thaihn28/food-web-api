package com.example.ecommerceweb.repository;

import com.example.ecommerceweb.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
