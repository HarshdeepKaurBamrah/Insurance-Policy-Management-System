package com.insurance.insurance_policy_management_system.service;

import com.insurance.insurance_policy_management_system.dto.PolicyRequestDto;
import com.insurance.insurance_policy_management_system.dto.PolicyResponseDto;
import java.util.List;

public interface PolicyService {

    PolicyResponseDto createPolicy(PolicyRequestDto requestDto);

    List<PolicyResponseDto> getAllPolicies();

    List<PolicyResponseDto> getActivePolicies();

    PolicyResponseDto getPolicyById(Long id);

    PolicyResponseDto updatePolicy(Long id, PolicyRequestDto requestDto);

    void deletePolicy(Long id);
}
