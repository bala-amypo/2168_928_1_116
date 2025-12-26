// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.math.BigDecimal;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "breach_reports")
// public class BreachReport {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     /* ========= RELATIONSHIP ========= */
//     @ManyToOne(optional = false)
//     @JoinColumn(name = "contract_id")
//     private Contract contract;

//     private Integer daysDelayed;

//     @Column(precision = 15, scale = 2)
//     private BigDecimal penaltyAmount;

//     private String remarks;

//     private LocalDateTime reportGeneratedAt;

//     @PrePersist
//     protected void onCreate() {
//         reportGeneratedAt = LocalDateTime.now();
//     }

//     public Long getId() { return id; }

//     public Contract getContract() { return contract; }
//     public void setContract(Contract contract) { this.contract = contract; }

//     public Integer getDaysDelayed() { return daysDelayed; }
//     public void setDaysDelayed(Integer daysDelayed) {
//         this.daysDelayed = daysDelayed;
//     }

//     public BigDecimal getPenaltyAmount() { return penaltyAmount; }
//     public void setPenaltyAmount(BigDecimal penaltyAmount) {
//         this.penaltyAmount = penaltyAmount;
//     }

//     public String getRemarks() { return remarks; }
//     public void setRemarks(String remarks) { this.remarks = remarks; }
// }
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    private Integer daysDelayed;

    private BigDecimal penaltyAmount;
}
