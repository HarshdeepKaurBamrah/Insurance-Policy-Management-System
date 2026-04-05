package com.insurance.insurance_policy_management_system.service.impl;

import com.insurance.insurance_policy_management_system.dto.PurchasePolicyRequestDto;
import com.insurance.insurance_policy_management_system.dto.PurchasePolicyResponseDto;
import com.insurance.insurance_policy_management_system.entity.Policy;
import com.insurance.insurance_policy_management_system.entity.PurchasePolicy;
import com.insurance.insurance_policy_management_system.entity.User;
import com.insurance.insurance_policy_management_system.entity.enums.PolicyStatus;
import com.insurance.insurance_policy_management_system.exception.BadRequestException;
import com.insurance.insurance_policy_management_system.exception.ResourceNotFoundException;
import com.insurance.insurance_policy_management_system.repository.PolicyRepository;
import com.insurance.insurance_policy_management_system.repository.PurchasePolicyRepository;
import com.insurance.insurance_policy_management_system.repository.UserRepository;
import com.insurance.insurance_policy_management_system.service.PurchasePolicyService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchasePolicyServiceImpl implements PurchasePolicyService {

    private final PurchasePolicyRepository purchasePolicyRepository;
    private final UserRepository userRepository;
    private final PolicyRepository policyRepository;

    @Override
    public PurchasePolicyResponseDto purchasePolicy(PurchasePolicyRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestDto.getUserId()));

        Policy policy = policyRepository.findById(requestDto.getPolicyId())
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found with id: " + requestDto.getPolicyId()));

        if (!Boolean.TRUE.equals(policy.getActive())) {
            throw new BadRequestException("Policy is not active and cannot be purchased");
        }

        LocalDate today = LocalDate.now();

        PurchasePolicy purchasePolicy = new PurchasePolicy();
        purchasePolicy.setUser(user);
        purchasePolicy.setPolicy(policy);
        purchasePolicy.setPurchaseDate(today);
        purchasePolicy.setStartDate(today);
        purchasePolicy.setEndDate(today.plusMonths(policy.getDurationInMonths()));
        purchasePolicy.setStatus(PolicyStatus.ACTIVE);

        return mapToDto(purchasePolicyRepository.save(purchasePolicy));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchasePolicyResponseDto> getAllPurchasedPolicies() {
        return purchasePolicyRepository.findAll().stream().map(this::mapToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PurchasePolicyResponseDto getPurchasedPolicyById(Long id) {
        PurchasePolicy purchasePolicy = findPurchasedPolicyById(id);
        return mapToDto(purchasePolicy);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchasePolicyResponseDto> getPurchasedPoliciesByUserId(Long userId) {
        return purchasePolicyRepository.findByUserId(userId).stream().map(this::mapToDto).toList();
    }

    @Override
    public PurchasePolicyResponseDto updatePurchasedPolicyStatus(Long id, PolicyStatus status) {
        PurchasePolicy purchasePolicy = findPurchasedPolicyById(id);
        purchasePolicy.setStatus(status);
        return mapToDto(purchasePolicyRepository.save(purchasePolicy));
    }

    private PurchasePolicy findPurchasedPolicyById(Long id) {
        return purchasePolicyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchased policy not found with id: " + id));
    }

    private PurchasePolicyResponseDto mapToDto(PurchasePolicy purchasePolicy) {
        PurchasePolicyResponseDto responseDto = new PurchasePolicyResponseDto();
        responseDto.setId(purchasePolicy.getId());
        responseDto.setUserId(purchasePolicy.getUser().getId());
        responseDto.setUserName(purchasePolicy.getUser().getFullName());
        responseDto.setPolicyId(purchasePolicy.getPolicy().getId());
        responseDto.setPolicyName(purchasePolicy.getPolicy().getPolicyName());
        responseDto.setPurchaseDate(purchasePolicy.getPurchaseDate());
        responseDto.setStartDate(purchasePolicy.getStartDate());
        responseDto.setEndDate(purchasePolicy.getEndDate());
        responseDto.setStatus(purchasePolicy.getStatus());
        return responseDto;
    }
}
