package com.insurance.insurance_policy_management_system.controller;

import com.insurance.insurance_policy_management_system.dto.ApiResponseDto;
import com.insurance.insurance_policy_management_system.dto.PolicyRequestDto;
import com.insurance.insurance_policy_management_system.dto.PolicyResponseDto;
import com.insurance.insurance_policy_management_system.service.PolicyService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @PostMapping
    public ResponseEntity<PolicyResponseDto> createPolicy(@Valid @RequestBody PolicyRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(policyService.createPolicy(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<PolicyResponseDto>> getAllPolicies() {
        return ResponseEntity.ok(policyService.getAllPolicies());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PolicyResponseDto>> getActivePolicies() {
        return ResponseEntity.ok(policyService.getActivePolicies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyResponseDto> getPolicyById(@PathVariable Long id) {
        return ResponseEntity.ok(policyService.getPolicyById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PolicyResponseDto> updatePolicy(
            @PathVariable Long id,
            @Valid @RequestBody PolicyRequestDto requestDto) {
        return ResponseEntity.ok(policyService.updatePolicy(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
        return ResponseEntity.ok(new ApiResponseDto("Policy deleted successfully"));
    }
}
