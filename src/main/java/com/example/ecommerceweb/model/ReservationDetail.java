package com.example.ecommerceweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservstion_id")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;


    private Integer numberOfTables;





}
