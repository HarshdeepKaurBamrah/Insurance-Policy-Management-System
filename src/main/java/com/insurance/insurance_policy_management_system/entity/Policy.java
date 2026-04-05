package com.insurance.insurance_policy_management_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "policies")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String policyName;

    @Column(nullable = false, length = 80)
    private String policyType;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal premiumAmount;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal coverageAmount;

    @Column(nullable = false)
    private Integer durationInMonths;

    @Column(nullable = false)
    private Boolean active;
}
