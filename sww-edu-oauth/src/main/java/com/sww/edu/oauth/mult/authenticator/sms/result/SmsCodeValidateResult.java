package com.sww.edu.oauth.mult.authenticator.sms.result;

import com.sww.edu.oauth.exception.AuthErrorType;
import lombok.Data;

@Data
public class SmsCodeValidateResult {
    private boolean success = true;
    private AuthErrorType authErrorType;
}
