package com.insurance.insurance_policy_management_system.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PurchasePolicyRequestDto {

    @NotNull(message = "User id is required")
    private Long userId;

    @NotNull(message = "Policy id is required")
    private Long policyId;
}
