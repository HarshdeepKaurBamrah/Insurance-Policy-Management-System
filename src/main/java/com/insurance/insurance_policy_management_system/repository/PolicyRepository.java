package com.insurance.insurance_policy_management_system.repository;

import com.insurance.insurance_policy_management_system.entity.Policy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

    List<Policy> findByActiveTrue();
}
