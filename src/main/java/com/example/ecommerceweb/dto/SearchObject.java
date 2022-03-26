package com.example.ecommerceweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class SearchObject {
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String toDate;
    private Boolean status;
    private String order;
    private Map<Boolean, String> options;
    private Map<String, String> sortOptions;

    public SearchObject() {

        this.order = "sendDESC";
        options = new HashMap<>();
        options.put(true, "Accepted");
        options.put(false, "Waiting");
        options.put(null, "Both");

        sortOptions = new HashMap<>();

    }
}
