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

    // getters & setters
}
