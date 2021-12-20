package com.vtca.color.reader.consumer.domain.color;

import com.vtca.color.reader.common.exception.BaseError;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public enum ColorError implements BaseError {
    COLOR_INVALID("COLOR_INVALID", "Color is invalid"),
    COLOR_ID("COLOR_ID", "Color ID is invalid"),
    COLOR_PRICE("COLOR_PRICE", "Color Price is invalid"),
    COLOR_NAME("COLOR_NAME", "Color Name is invalid"),
    COLOR_NOT_EXIST("COLOR_NOT_EXIST", "Color is not existing");

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
