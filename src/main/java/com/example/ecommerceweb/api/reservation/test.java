package com.example.ecommerceweb.api.reservation;

import com.example.ecommerceweb.dto.ProductDTO;
import com.example.ecommerceweb.dto.ReservationDTO;
import com.example.ecommerceweb.model.Reservation;
import com.example.ecommerceweb.model.ReservationDetail;
import com.example.ecommerceweb.model.ResponseObject;
import com.example.ecommerceweb.model.Table;
import com.example.ecommerceweb.repository.ReservationDetailRepository;
import com.example.ecommerceweb.repository.ReservationRepository;
import com.example.ecommerceweb.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class test {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservationDetailRepository reservationDetailRepository;
    @Autowired
    TableRepository tableRepository;

    @GetMapping("/addTable")
    public Table add() {

//        return new Table(12L, 5);
        return  null;
    }

    @GetMapping("/viewRes")
    public Reservation viewReservation() {
//        Table table = new Table(1L, 2);
//        Table table1 = new Table(2L, 6);
//
//       tableRepository.save(table);
//        tableRepository.save(table1);
//
   //     long millis = System.currentTimeMillis();
    //    java.sql.Date date = new java.sql.Date(millis);
//
 //     List<ReservationDetail> detail = new ArrayList<>();
//
//        Reservation res = new Reservation(null, "Xin chao moi nguoi",
//                date, detail, "Truong", "0981108706", "Hanoi", false);

//        ReservationDetail res2 = new ReservationDetail(null, res, table, 5);
//        ReservationDetail res1 = new ReservationDetail(null, res, table1, 12);
//        reservationDetailRepository.save(res2);
//        reservationDetailRepository.save(res1);
//        detail.add(res2);
//        detail.add(res1);
//
//        res.setReservationDetails(detail);
//        reservationRepository.save(res);
        Reservation res = reservationRepository.getById(1L);
//        res.setReservationDetails(null);

       return res;

    }
    @GetMapping("/viewRes2")
    public String viewReservation2() {
        Table table = tableRepository.getById(1L);
        Table table1 = tableRepository.getById(3L);

        ReservationDetail res2 = new ReservationDetail(1L, null, table, 5);
        ReservationDetail res1 = new ReservationDetail(2L, null, table1, 12);

        reservationDetailRepository.save(res1);
        reservationDetailRepository.save(res2);
        return "ok";
    }

    @PostMapping("/test2")
    public ResponseEntity<ResponseObject> test(@RequestBody ReservationDTO reservationDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "insert success", reservationDTO)
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> test3(@RequestBody Reservation newReservation, @PathVariable Long id) {
        Optional<Reservation> res = reservationRepository.findById(id)
                .map(reservation -> {
                   reservation.setPhoneNumber(newReservation.getPhoneNumber());
                   return reservationRepository.save(reservation);
                });
        return null;    }

}
