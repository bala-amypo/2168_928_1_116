package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(
    name = "contracts",
    uniqueConstraints = @UniqueConstraint(columnNames = "contractNumber")
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Contract number is required")
    private String contractNumber;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Counterparty name is required")
    private String counterpartyName;

    @NotNull(message = "Agreed delivery date is required")
    @Temporal(TemporalType.DATE)
    private Date agreedDeliveryDate;

    @NotNull(message = "Base contract value is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal baseContractValue;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
        status = "ACTIVE";
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = new Date();
    }
}
