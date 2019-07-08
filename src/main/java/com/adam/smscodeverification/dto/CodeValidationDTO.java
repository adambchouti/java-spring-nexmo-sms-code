package com.adam.smscodeverification.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CodeValidationDTO {
    private String requestId;
    private String code;
    private boolean success;

    public CodeValidationDTO(String requestId, boolean success) {
        this.requestId = requestId;
        this.success = success;
    }
}
