package com.adam.smscodeverification.services;

import com.adam.smscodeverification.dto.CodeValidationDTO;
import com.adam.smscodeverification.dto.PhoneVerificationDTO;
import com.nexmo.client.NexmoClientException;

import java.io.IOException;

public interface NexmoService {
    /**
     * Receives a phone number and send an sms code verification associated with the phone number
     * @param phoneVerificationDTO Phone number to validate
     * @return Success sending the sms or not
     * @throws IOException
     * @throws NexmoClientException
     */
    CodeValidationDTO phoneVerification(PhoneVerificationDTO phoneVerificationDTO) throws IOException, NexmoClientException;

    /**
     * Validates the code for a given phone number
     * @param codeValidationDTO Code to check
     * @return true if validation success else false
     * @throws IOException
     * @throws NexmoClientException
     */
    boolean phoneCodeVerification(CodeValidationDTO codeValidationDTO) throws IOException, NexmoClientException;
}
