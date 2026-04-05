package com.insurance.insurance_policy_management_system.service.impl;

import com.insurance.insurance_policy_management_system.dto.UserRequestDto;
import com.insurance.insurance_policy_management_system.dto.UserResponseDto;
import com.insurance.insurance_policy_management_system.entity.User;
import com.insurance.insurance_policy_management_system.exception.BadRequestException;
import com.insurance.insurance_policy_management_system.exception.ResourceNotFoundException;
import com.insurance.insurance_policy_management_system.repository.UserRepository;
import com.insurance.insurance_policy_management_system.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new BadRequestException("User with this email already exists");
        }

        User user = new User();
        mapToEntity(requestDto, user);

        return mapToDto(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        User user = findUserById(id);
        return mapToDto(user);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User existingUser = findUserById(id);

        userRepository.findByEmail(requestDto.getEmail())
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new BadRequestException("Email is already used by another user");
                });

        mapToEntity(requestDto, existingUser);
        return mapToDto(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    private void mapToEntity(UserRequestDto requestDto, User user) {
        user.setFullName(requestDto.getFullName());
        user.setEmail(requestDto.getEmail());
        user.setPhone(requestDto.getPhone());
        user.setAddress(requestDto.getAddress());
    }

    private UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setFullName(user.getFullName());
        responseDto.setEmail(user.getEmail());
        responseDto.setPhone(user.getPhone());
        responseDto.setAddress(user.getAddress());
        return responseDto;
    }
}
