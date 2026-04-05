package com.insurance.insurance_policy_management_system.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
}
