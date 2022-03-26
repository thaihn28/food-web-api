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
    private Boolean status = false;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Map<Boolean, String> getOptions() {
        return options;
    }

    public void setOptions(Map<Boolean, String> options) {
        this.options = options;
    }
}
