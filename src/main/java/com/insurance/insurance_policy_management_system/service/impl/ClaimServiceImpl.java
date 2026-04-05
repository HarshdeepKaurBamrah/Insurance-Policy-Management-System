package com.insurance.insurance_policy_management_system.service.impl;

import com.insurance.insurance_policy_management_system.dto.ClaimRequestDto;
import com.insurance.insurance_policy_management_system.dto.ClaimResponseDto;
import com.insurance.insurance_policy_management_system.dto.ClaimReviewRequestDto;
import com.insurance.insurance_policy_management_system.entity.Claim;
import com.insurance.insurance_policy_management_system.entity.PurchasePolicy;
import com.insurance.insurance_policy_management_system.entity.enums.ClaimStatus;
import com.insurance.insurance_policy_management_system.entity.enums.PolicyStatus;
import com.insurance.insurance_policy_management_system.exception.BadRequestException;
import com.insurance.insurance_policy_management_system.exception.ResourceNotFoundException;
import com.insurance.insurance_policy_management_system.repository.ClaimRepository;
import com.insurance.insurance_policy_management_system.repository.PurchasePolicyRepository;
import com.insurance.insurance_policy_management_system.service.ClaimService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final PurchasePolicyRepository purchasePolicyRepository;

    @Override
    public ClaimResponseDto submitClaim(ClaimRequestDto requestDto) {
        PurchasePolicy purchasePolicy = purchasePolicyRepository.findById(requestDto.getPurchasePolicyId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Purchased policy not found with id: " + requestDto.getPurchasePolicyId()));

        if (!PolicyStatus.ACTIVE.equals(purchasePolicy.getStatus())) {
            throw new BadRequestException("Claim can only be submitted for an active purchased policy");
        }

        Claim claim = new Claim();
        claim.setPurchasePolicy(purchasePolicy);
        claim.setClaimAmount(requestDto.getClaimAmount());
        claim.setReason(requestDto.getReason());
        claim.setIncidentDate(requestDto.getIncidentDate());
        claim.setDocumentUrl(requestDto.getDocumentUrl());
        claim.setClaimDate(LocalDate.now());
        claim.setStatus(ClaimStatus.PENDING);

        return mapToDto(claimRepository.save(claim));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClaimResponseDto> getAllClaims() {
        return claimRepository.findAll().stream().map(this::mapToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ClaimResponseDto getClaimById(Long id) {
        Claim claim = findClaimById(id);
        return mapToDto(claim);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClaimResponseDto> getClaimsByUserId(Long userId) {
        return claimRepository.findByPurchasePolicyUserId(userId).stream().map(this::mapToDto).toList();
    }

    @Override
    public ClaimResponseDto reviewClaim(Long id, ClaimReviewRequestDto requestDto) {
        Claim claim = findClaimById(id);

        if (ClaimStatus.PENDING != claim.getStatus()) {
            throw new BadRequestException("Only pending claims can be reviewed");
        }
        if (ClaimStatus.PENDING.equals(requestDto.getStatus())) {
            throw new BadRequestException("Claim review status must be APPROVED or REJECTED");
        }

        claim.setStatus(requestDto.getStatus());
        claim.setAdminRemarks(requestDto.getAdminRemarks());

        return mapToDto(claimRepository.save(claim));
    }

    private Claim findClaimById(Long id) {
        return claimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found with id: " + id));
    }

    private ClaimResponseDto mapToDto(Claim claim) {
        ClaimResponseDto responseDto = new ClaimResponseDto();
        responseDto.setId(claim.getId());
        responseDto.setPurchasePolicyId(claim.getPurchasePolicy().getId());
        responseDto.setUserId(claim.getPurchasePolicy().getUser().getId());
        responseDto.setUserName(claim.getPurchasePolicy().getUser().getFullName());
        responseDto.setClaimAmount(claim.getClaimAmount());
        responseDto.setReason(claim.getReason());
        responseDto.setIncidentDate(claim.getIncidentDate());
        responseDto.setDocumentUrl(claim.getDocumentUrl());
        responseDto.setClaimDate(claim.getClaimDate());
        responseDto.setStatus(claim.getStatus());
        responseDto.setAdminRemarks(claim.getAdminRemarks());
        return responseDto;
    }
}
