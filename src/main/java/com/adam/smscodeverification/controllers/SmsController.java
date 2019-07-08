package com.adam.smscodeverification.controllers;

import com.adam.smscodeverification.dto.CodeValidationDTO;
import com.adam.smscodeverification.dto.PhoneVerificationDTO;
import com.adam.smscodeverification.services.NexmoService;
import com.nexmo.client.NexmoClientException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    private final NexmoService nexmoService;

    public SmsController(NexmoService nexmoService) {
        this.nexmoService = nexmoService;
    }

    @PostMapping
    public CodeValidationDTO sendCodeSms(@RequestBody PhoneVerificationDTO phoneVerificationDTO) throws IOException, NexmoClientException {
        return this.nexmoService.phoneVerification(phoneVerificationDTO);
    }

    @PostMapping("/verify")
    public boolean verifyCode(@RequestBody CodeValidationDTO codeValidationDTO) throws IOException, NexmoClientException {
        return this.nexmoService.phoneCodeVerification(codeValidationDTO);
    }
}
