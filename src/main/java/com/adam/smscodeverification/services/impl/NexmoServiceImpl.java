package com.adam.smscodeverification.services.impl;

import com.adam.smscodeverification.dto.CodeValidationDTO;
import com.adam.smscodeverification.dto.PhoneVerificationDTO;
import com.adam.smscodeverification.services.NexmoService;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.verify.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class NexmoServiceImpl implements NexmoService {

    private VerifyClient verifyClient;

    public NexmoServiceImpl() {
        NexmoClient client = NexmoClient.builder().apiKey("USE_API_KEY")
                .apiSecret("USE_API_SECRET")
                .build();
        verifyClient = client.getVerifyClient();
    }

    @Override
    public CodeValidationDTO phoneVerification(PhoneVerificationDTO phoneVerificationDTO) throws IOException, NexmoClientException {
        log.info("Phone verification for " + phoneVerificationDTO.getPhoneNumber());

        VerifyRequest request = new VerifyRequest(phoneVerificationDTO.getPhoneNumber(), "SMS-CODE");
        request.setLength(4);

        VerifyResponse response = verifyClient.verify(request);

        if (response.getStatus() == VerifyStatus.OK)
            return new CodeValidationDTO(response.getRequestId(), true);
        else {
            log.info("Sending phone verification FAILED - " + response.getStatus() + ": " + response.getErrorText());
            return new CodeValidationDTO("", false);
        }
    }

    @Override
    public boolean phoneCodeVerification(CodeValidationDTO codeValidationDTO) throws IOException, NexmoClientException {
        log.info("Validating code for requestId: " + codeValidationDTO.getRequestId() + " - Code: " + codeValidationDTO.getCode());

        String requestId = codeValidationDTO.getRequestId();
        CheckResponse response = verifyClient.check(requestId, codeValidationDTO.getCode());

        if (response.getStatus() == VerifyStatus.OK) {
            log.info("Validation SUCCESS - Price: " + response.getPrice());
            return true;
        } else {
            log.info("Validation FAILED - " + response.getStatus() + ": " + response.getErrorText());
            return false;
        }
    }
}
