package com.insurance.insurance_policy_management_system.dto;

import com.insurance.insurance_policy_management_system.entity.enums.ClaimStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClaimResponseDto {

    private Long id;
    private Long purchasePolicyId;
    private Long userId;
    private String userName;
    private BigDecimal claimAmount;
    private String reason;
    private LocalDate incidentDate;
    private String documentUrl;
    private LocalDate claimDate;
    private ClaimStatus status;
    private String adminRemarks;
}
