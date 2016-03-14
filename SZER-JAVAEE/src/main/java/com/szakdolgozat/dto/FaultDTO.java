package com.szakdolgozat.dto;

import java.util.Date;

/**
 * Created by Ákos on 2016.03.14..
 */
public class FaultDTO {

    private Long id;
    private String customerName;
    private String faultTitle;
    private Date reportedDate;

    public FaultDTO(){};

    public FaultDTO(Long id, String name, String title, Date reportedDate){
        this.id=id;
        this.customerName=name;
        this.faultTitle=title;
        this.reportedDate=reportedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFaultTitle() {
        return faultTitle;
    }

    public void setFaultTitle(String faultTitle) {
        this.faultTitle = faultTitle;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }
}
