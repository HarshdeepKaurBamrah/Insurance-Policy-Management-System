package com.insurance.insurance_policy_management_system.service;

import com.insurance.insurance_policy_management_system.dto.ClaimRequestDto;
import com.insurance.insurance_policy_management_system.dto.ClaimResponseDto;
import com.insurance.insurance_policy_management_system.dto.ClaimReviewRequestDto;
import java.util.List;

public interface ClaimService {

    ClaimResponseDto submitClaim(ClaimRequestDto requestDto);

    List<ClaimResponseDto> getAllClaims();

    ClaimResponseDto getClaimById(Long id);

    List<ClaimResponseDto> getClaimsByUserId(Long userId);

    ClaimResponseDto reviewClaim(Long id, ClaimReviewRequestDto requestDto);
}
