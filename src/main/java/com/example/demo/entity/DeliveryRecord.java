package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "delivery_record")
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Contract is required")
    @ManyToOne(optional = false)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @NotNull(message = "Delivery date is required")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    private String notes;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
