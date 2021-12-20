package com.vtca.color.reader.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class BusinessException extends RuntimeException {
    private String code;
    private String message;

    public BusinessException (BaseError error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }
}
