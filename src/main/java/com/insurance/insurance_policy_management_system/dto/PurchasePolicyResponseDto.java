package com.insurance.insurance_policy_management_system.dto;

import com.insurance.insurance_policy_management_system.entity.enums.PolicyStatus;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PurchasePolicyResponseDto {

    private Long id;
    private Long userId;
    private String userName;
    private Long policyId;
    private String policyName;
    private LocalDate purchaseDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private PolicyStatus status;
}
