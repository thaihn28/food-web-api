package com.example.ecommerceweb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
public class Reservation implements Comparable<Reservation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String note;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+7")
    private Date appointmentTime;

    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+7")
    private LocalTime appointmentHour;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.REMOVE)
    private List<ReservationDetail> reservationDetails;
    @NotNull
    private String clientName;
    @NotNull
    @Column(length = 10)
    private String phoneNumber;
    @NotNull
    private String address;

    private boolean isApprove;

    private boolean complete;

    public Reservation() {
        isApprove = false;
        complete = false;
    }

    public String getRealDate() {
        SimpleDateFormat spm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String realDate = spm.format(date).toString();
        return realDate;
    }

    public boolean isApprove() {
        return isApprove;
    }

    public void setApprove(boolean approve) {
        isApprove = approve;
    }

    @Override
    public int compareTo(Reservation o) {
        if (this.getDate().after(o.getDate())) {
            return -1;
        } else if (this.getDate().before(o.getDate())) {
            return 1;
        } else {
            return 0;
        }
    }
}
