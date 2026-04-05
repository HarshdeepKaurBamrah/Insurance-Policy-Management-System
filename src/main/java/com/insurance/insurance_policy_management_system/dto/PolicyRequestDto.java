package com.insurance.insurance_policy_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PolicyRequestDto {

    @NotBlank(message = "Policy name is required")
    @Size(min = 3, max = 100, message = "Policy name must be between 3 and 100 characters")
    private String policyName;

    @NotBlank(message = "Policy type is required")
    @Size(min = 3, max = 80, message = "Policy type must be between 3 and 80 characters")
    private String policyType;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @NotNull(message = "Premium amount is required")
    @Positive(message = "Premium amount must be positive")
    private BigDecimal premiumAmount;

    @NotNull(message = "Coverage amount is required")
    @Positive(message = "Coverage amount must be positive")
    private BigDecimal coverageAmount;

    @NotNull(message = "Duration in months is required")
    @Positive(message = "Duration in months must be positive")
    private Integer durationInMonths;

    @NotNull(message = "Policy active flag is required")
    private Boolean active;
}
