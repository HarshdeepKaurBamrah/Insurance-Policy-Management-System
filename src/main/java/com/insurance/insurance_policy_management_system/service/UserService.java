package com.insurance.insurance_policy_management_system.service;

import com.insurance.insurance_policy_management_system.dto.UserRequestDto;
import com.insurance.insurance_policy_management_system.dto.UserResponseDto;
import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto requestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    UserResponseDto updateUser(Long id, UserRequestDto requestDto);

    void deleteUser(Long id);
}
