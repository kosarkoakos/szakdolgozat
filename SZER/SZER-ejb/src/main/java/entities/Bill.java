/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ákos
 */
@Entity
@Table(name="Bills")
public class Bill implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name="BILL_ID")
    private Long billId;
    
    @Column(name="AMOUNT")
    private Integer amount;
    
    @Column(name="DEADLINE")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    
    @ManyToOne
    @JoinColumn(name="ORDER_ID_FK", referencedColumnName="orderId")
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
}
