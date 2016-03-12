package com.szakdolgozat.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="Bills")
public class Bill implements Serializable{
    @Id
    @GeneratedValue
    @Column(name="BILL_ID")
    private Long billId;

    @Column(name = "BILLNAME")
    private String billName;

    @Column(name="AMOUNT")
    private Integer amount;

    @Column(name="DEADLINE")
    @Temporal(TemporalType.DATE)
    private Date deadline;

    @ManyToOne
    @JoinColumn(name="ORDER_ID_FK", referencedColumnName="ORDER_ID")
    private Order order;

    public Bill(){

    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }
}
