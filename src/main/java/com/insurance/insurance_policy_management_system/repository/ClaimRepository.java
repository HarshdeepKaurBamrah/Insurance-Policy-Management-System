package com.insurance.insurance_policy_management_system.repository;

import com.insurance.insurance_policy_management_system.entity.Claim;
import com.insurance.insurance_policy_management_system.entity.enums.ClaimStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    List<Claim> findByPurchasePolicyUserId(Long userId);

    List<Claim> findByStatus(ClaimStatus status);
}
