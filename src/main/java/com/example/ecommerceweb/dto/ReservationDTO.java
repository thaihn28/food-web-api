package com.example.ecommerceweb.dto;


import com.example.ecommerceweb.model.ReservationDetail;
import com.example.ecommerceweb.model.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Long id;
    @Lob
    private String note;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date date;

    @NotNull
    private String clientName;
    @NotNull
    @Column(length = 10)
    private String phoneNumber;
    @NotNull
    private String address;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+7")
    private Date appointmentTime;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+7")
    private LocalTime appointmentHour;

    List<TableDTO> table;
}
