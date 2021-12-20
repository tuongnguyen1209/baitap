package com.vtca.color.reader.consumer.domain.color.reference;

import com.vtca.color.reader.common.exception.BaseError;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public enum ColorReferenceError implements BaseError {
    COLOR_REFERENCE_INVALID("COLOR_REFERENCE_INVALID", "Color Reference is invalid");

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
