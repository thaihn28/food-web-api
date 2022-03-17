package com.example.ecommerceweb.api.reservation;

import com.example.ecommerceweb.dto.ReservationDTO;
import com.example.ecommerceweb.dto.TableDTO;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationAPI {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    TableRepository tableRepository;
    @Autowired
    ReservationDetailRepository reservationDetailRepository;

    @PostMapping("/new-reservation")
    public ResponseEntity<ResponseObject> newRes(@RequestBody ReservationDTO resTemp) throws ParseException {
        Reservation res = new Reservation();
        res.setClientName(resTemp.getClientName());

//        SimpleDateFormat spm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        String realDate = spm.format(resTemp.getDate());
        res.setDate(resTemp.getDate());

        res.setAddress(resTemp.getAddress());
        res.setPhoneNumber(resTemp.getPhoneNumber());
        res.setNote(resTemp.getNote());
        res.setApprove(false);
        reservationRepository.save(res);

        List<ReservationDetail> reservationDetailList = new ArrayList<>();

        for (int i=0; i < resTemp.getTable().size(); i++) {

            TableDTO tableDTO = resTemp.getTable().get(i);
            Table table = tableRepository.findTableByTableTypes(tableDTO.getType());



            ReservationDetail reservationDetail = new ReservationDetail();
            reservationDetail.setReservation(res);
            reservationDetail.setTable(table);
            reservationDetail.setNumberOfTables(tableDTO.getNumberOfTable());

            reservationDetailList.add(reservationDetail);

            reservationDetailRepository.save(reservationDetail);
        }

        res.setReservationDetails(reservationDetailList);
        //reservationRepository.save(res);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "New reservation added",
               resTemp));
    }
}
