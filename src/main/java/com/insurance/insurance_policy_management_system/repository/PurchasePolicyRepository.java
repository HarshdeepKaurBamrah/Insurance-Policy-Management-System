package com.insurance.insurance_policy_management_system.repository;

import com.insurance.insurance_policy_management_system.entity.PurchasePolicy;
import com.insurance.insurance_policy_management_system.entity.enums.PolicyStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasePolicyRepository extends JpaRepository<PurchasePolicy, Long> {

    List<PurchasePolicy> findByUserId(Long userId);

    List<PurchasePolicy> findByStatus(PolicyStatus status);
}
