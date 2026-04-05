package com.insurance.insurance_policy_management_system.service;

import com.insurance.insurance_policy_management_system.dto.PurchasePolicyRequestDto;
import com.insurance.insurance_policy_management_system.dto.PurchasePolicyResponseDto;
import com.insurance.insurance_policy_management_system.entity.enums.PolicyStatus;
import java.util.List;

public interface PurchasePolicyService {

    PurchasePolicyResponseDto purchasePolicy(PurchasePolicyRequestDto requestDto);

    List<PurchasePolicyResponseDto> getAllPurchasedPolicies();

    PurchasePolicyResponseDto getPurchasedPolicyById(Long id);

    List<PurchasePolicyResponseDto> getPurchasedPoliciesByUserId(Long userId);

    PurchasePolicyResponseDto updatePurchasedPolicyStatus(Long id, PolicyStatus status);
}
