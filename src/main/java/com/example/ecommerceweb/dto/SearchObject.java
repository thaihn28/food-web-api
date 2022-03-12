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
    private Integer startPage;
    private Integer endPage;
    private String order;
    private Map<Boolean, String> options;

    public SearchObject() {
      this.order = "ascending";
      options = new HashMap<>();
      options.put(true, "Accepted");
      options.put(false, "Waiting");
      options.put(null, "Both");

    }
}
