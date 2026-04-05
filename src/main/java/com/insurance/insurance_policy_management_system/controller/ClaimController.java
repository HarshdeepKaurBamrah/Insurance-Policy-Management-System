package com.insurance.insurance_policy_management_system.controller;

import com.insurance.insurance_policy_management_system.dto.ClaimRequestDto;
import com.insurance.insurance_policy_management_system.dto.ClaimResponseDto;
import com.insurance.insurance_policy_management_system.dto.ClaimReviewRequestDto;
import com.insurance.insurance_policy_management_system.service.ClaimService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    @PostMapping
    public ResponseEntity<ClaimResponseDto> submitClaim(@Valid @RequestBody ClaimRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(claimService.submitClaim(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<ClaimResponseDto>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaimResponseDto> getClaimById(@PathVariable Long id) {
        return ResponseEntity.ok(claimService.getClaimById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ClaimResponseDto>> getClaimsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(claimService.getClaimsByUserId(userId));
    }

    @PutMapping("/{id}/review")
    public ResponseEntity<ClaimResponseDto> reviewClaim(@PathVariable Long id, @Valid @RequestBody ClaimReviewRequestDto requestDto) {
        return ResponseEntity.ok(claimService.reviewClaim(id, requestDto));
    }
}
