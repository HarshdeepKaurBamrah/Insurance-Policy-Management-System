package com.insurance.insurance_policy_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClaimRequestDto {

    @NotNull(message = "Purchase policy id is required")
    private Long purchasePolicyId;

    @NotNull(message = "Claim amount is required")
    @Positive(message = "Claim amount must be positive")
    private BigDecimal claimAmount;

    @NotBlank(message = "Claim reason is required")
    @Size(min = 10, max = 500, message = "Reason must be between 10 and 500 characters")
    private String reason;

    @NotNull(message = "Incident date is required")
    @PastOrPresent(message = "Incident date must be in the past or present")
    private LocalDate incidentDate;

    @NotBlank(message = "Document URL is required")
    @Size(min = 5, max = 255, message = "Document URL must be between 5 and 255 characters")
    private String documentUrl;
}
