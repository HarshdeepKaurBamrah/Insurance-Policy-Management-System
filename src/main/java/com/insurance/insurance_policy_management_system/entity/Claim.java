package com.insurance.insurance_policy_management_system.entity;

import com.insurance.insurance_policy_management_system.entity.enums.ClaimStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purchase_policy_id", nullable = false)
    private PurchasePolicy purchasePolicy;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal claimAmount;

    @Column(nullable = false, length = 500)
    private String reason;

    @Column(nullable = false)
    private LocalDate incidentDate;

    @Column(nullable = false, length = 255)
    private String documentUrl;

    @Column(nullable = false)
    private LocalDate claimDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ClaimStatus status;

    @Column(length = 500)
    private String adminRemarks;
}
