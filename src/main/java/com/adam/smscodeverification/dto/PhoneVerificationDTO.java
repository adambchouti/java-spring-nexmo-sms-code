package com.adam.smscodeverification.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhoneVerificationDTO {
    private String phoneNumber;
}
