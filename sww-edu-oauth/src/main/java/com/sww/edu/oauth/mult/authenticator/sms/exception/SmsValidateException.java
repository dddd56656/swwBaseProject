package com.sww.edu.oauth.mult.authenticator.sms.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sww.edu.oauth.exception.CustomOauthExceptionSerializer;
import com.sww.edu.oauth.mult.authenticator.sms.result.SmsCodeValidateResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class SmsValidateException extends RuntimeException {
    private SmsCodeValidateResult result;

    public SmsValidateException(SmsCodeValidateResult result) {
        this.result = result;
    }
}
