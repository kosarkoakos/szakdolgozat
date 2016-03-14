package com.szakdolgozat.entities;

import com.szakdolgozat.entities.person.Customer;
import com.szakdolgozat.enums.ServiceType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ákos on 2016.03.06..
 */
@Entity
@Table(name="ReportedFaults")
public class ReportedFault implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "FAULT_ID")
    private Long id;

    @Column(name = "SERVICETYPE")
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "REPORTEDDATE")
    private Date reportDate;

    @Column(name = "SOLVEDDATE")
    private Date solvedDate;

    @ManyToOne
    @JoinColumn(name="USER_ID_FK", referencedColumnName="USER_ID")
    Customer reporter;

    @Column(name = "SOLUTION")
    private String solution;

    public ReportedFault(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Customer getReporter() {
        return reporter;
    }

    public void setReporter(Customer reporter) {
        this.reporter = reporter;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Date getSolvedDate() {
        return solvedDate;
    }

    public void setSolvedDate(Date solvedDate) {
        this.solvedDate = solvedDate;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
