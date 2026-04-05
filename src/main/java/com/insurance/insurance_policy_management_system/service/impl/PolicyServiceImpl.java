package com.insurance.insurance_policy_management_system.service.impl;

import com.insurance.insurance_policy_management_system.dto.PolicyRequestDto;
import com.insurance.insurance_policy_management_system.dto.PolicyResponseDto;
import com.insurance.insurance_policy_management_system.entity.Policy;
import com.insurance.insurance_policy_management_system.exception.ResourceNotFoundException;
import com.insurance.insurance_policy_management_system.repository.PolicyRepository;
import com.insurance.insurance_policy_management_system.service.PolicyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;

    @Override
    public PolicyResponseDto createPolicy(PolicyRequestDto requestDto) {
        Policy policy = new Policy();
        mapToEntity(requestDto, policy);
        return mapToDto(policyRepository.save(policy));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PolicyResponseDto> getAllPolicies() {
        return policyRepository.findAll().stream().map(this::mapToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PolicyResponseDto> getActivePolicies() {
        return policyRepository.findByActiveTrue().stream().map(this::mapToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PolicyResponseDto getPolicyById(Long id) {
        Policy policy = findPolicyById(id);
        return mapToDto(policy);
    }

    @Override
    public PolicyResponseDto updatePolicy(Long id, PolicyRequestDto requestDto) {
        Policy existingPolicy = findPolicyById(id);
        mapToEntity(requestDto, existingPolicy);
        return mapToDto(policyRepository.save(existingPolicy));
    }

    @Override
    public void deletePolicy(Long id) {
        Policy policy = findPolicyById(id);
        policyRepository.delete(policy);
    }

    private Policy findPolicyById(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found with id: " + id));
    }

    private void mapToEntity(PolicyRequestDto requestDto, Policy policy) {
        policy.setPolicyName(requestDto.getPolicyName());
        policy.setPolicyType(requestDto.getPolicyType());
        policy.setDescription(requestDto.getDescription());
        policy.setPremiumAmount(requestDto.getPremiumAmount());
        policy.setCoverageAmount(requestDto.getCoverageAmount());
        policy.setDurationInMonths(requestDto.getDurationInMonths());
        policy.setActive(requestDto.getActive());
    }

    private PolicyResponseDto mapToDto(Policy policy) {
        PolicyResponseDto responseDto = new PolicyResponseDto();
        responseDto.setId(policy.getId());
        responseDto.setPolicyName(policy.getPolicyName());
        responseDto.setPolicyType(policy.getPolicyType());
        responseDto.setDescription(policy.getDescription());
        responseDto.setPremiumAmount(policy.getPremiumAmount());
        responseDto.setCoverageAmount(policy.getCoverageAmount());
        responseDto.setDurationInMonths(policy.getDurationInMonths());
        responseDto.setActive(policy.getActive());
        return responseDto;
    }
}
