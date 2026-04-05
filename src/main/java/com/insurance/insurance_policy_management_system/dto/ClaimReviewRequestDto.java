package com.insurance.insurance_policy_management_system.dto;

import com.insurance.insurance_policy_management_system.entity.enums.ClaimStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClaimReviewRequestDto {

    @NotNull(message = "Claim status is required")
    private ClaimStatus status;

    @NotBlank(message = "Admin remarks are required")
    @Size(min = 5, max = 500, message = "Admin remarks must be between 5 and 500 characters")
    private String adminRemarks;
}
