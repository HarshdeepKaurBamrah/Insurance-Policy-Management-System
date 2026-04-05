package com.insurance.insurance_policy_management_system.dto;

import com.insurance.insurance_policy_management_system.entity.enums.PolicyStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PurchasePolicyStatusUpdateRequestDto {

    @NotNull(message = "Policy status is required")
    private PolicyStatus status;
}
