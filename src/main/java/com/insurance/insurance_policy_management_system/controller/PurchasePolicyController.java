package com.insurance.insurance_policy_management_system.controller;

import com.insurance.insurance_policy_management_system.dto.PurchasePolicyRequestDto;
import com.insurance.insurance_policy_management_system.dto.PurchasePolicyResponseDto;
import com.insurance.insurance_policy_management_system.dto.PurchasePolicyStatusUpdateRequestDto;
import com.insurance.insurance_policy_management_system.service.PurchasePolicyService;
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
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchasePolicyController {

    private final PurchasePolicyService purchasePolicyService;

    @PostMapping
    public ResponseEntity<PurchasePolicyResponseDto> purchasePolicy(@Valid @RequestBody PurchasePolicyRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchasePolicyService.purchasePolicy(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<PurchasePolicyResponseDto>> getAllPurchasedPolicies() {
        return ResponseEntity.ok(purchasePolicyService.getAllPurchasedPolicies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchasePolicyResponseDto> getPurchasedPolicyById(@PathVariable Long id) {
        return ResponseEntity.ok(purchasePolicyService.getPurchasedPolicyById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PurchasePolicyResponseDto>> getPurchasedPoliciesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(purchasePolicyService.getPurchasedPoliciesByUserId(userId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PurchasePolicyResponseDto> updatePurchasedPolicyStatus(
            @PathVariable Long id,
            @Valid @RequestBody PurchasePolicyStatusUpdateRequestDto requestDto) {
        return ResponseEntity.ok(purchasePolicyService.updatePurchasedPolicyStatus(id, requestDto.getStatus()));
    }
}
