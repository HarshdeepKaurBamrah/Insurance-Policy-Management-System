package com.insurance.insurance_policy_management_system.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PolicyResponseDto {

    private Long id;
    private String policyName;
    private String policyType;
    private String description;
    private BigDecimal premiumAmount;
    private BigDecimal coverageAmount;
    private Integer durationInMonths;
    private Boolean active;
}
