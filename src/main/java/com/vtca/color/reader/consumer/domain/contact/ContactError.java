package com.vtca.color.reader.consumer.domain.contact;

import com.vtca.color.reader.common.exception.BaseError;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public enum ContactError implements BaseError {
    CONTACT_EMPTY("CONTACT_EMPTY", "Contact is empty"),
    EMAIL_EMPTY("EMAIL_EMPTY", "Email is empty"),
    SUBJECT_EMPTY("SUBJECT_EMPTY", "Subject is empty"),
    MESSAGE_EMPTY("MESSAGE_EMPTY", "Message is empty"),
    EMAIL_PATTERN("EMAIL_PATTERN", "Email is wrong pattern");

    private String code;
    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
