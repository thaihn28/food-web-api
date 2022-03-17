package com.example.ecommerceweb;


import com.example.ecommerceweb.model.Table;
import com.example.ecommerceweb.repository.ReservationDetailRepository;
import com.example.ecommerceweb.repository.ReservationRepository;
import com.example.ecommerceweb.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservationDetailRepository reservationDetailRepository;
    @Autowired
     static TableRepository tableRepository;

    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat spm = new SimpleDateFormat("yyyy/MM/dd");
        String realDate = spm.format(date);
        System.out.println(realDate);

        String date1 = "2022/03/10";
        String date2 = "2022-03-10 10:26";
        System.out.println(spm.parse(date1));
        //System.out.println(spm.parse(date1).after(spm.parse(date2)));
    }
}
