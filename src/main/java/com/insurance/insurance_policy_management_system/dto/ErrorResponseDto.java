package com.insurance.insurance_policy_management_system.dto;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponseDto {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> validationErrors;
}
