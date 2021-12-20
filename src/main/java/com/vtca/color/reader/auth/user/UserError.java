package com.vtca.color.reader.auth.user;

import com.vtca.color.reader.common.exception.BaseError;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public enum UserError implements BaseError {
    USER_NOT_EXIST("USER_NOT_EXIST", "User is NOT existed in system"),
    USER_EMPTY("USER_EMPTY", "User is empty"),
    ID_EMPTY("ID_EMPTY", "User ID is empty"),
    USERNAME_EMPTY("USERNAME_EMPTY", "Username is empty"),
    USERNAME_NOT_EXIST("USERNAME_NOT_EXIST", "Username is NOT existed in system"),
    PASSWORD_EMPTY("PASSWORD_EMPTY", "Password is empty"),
    ROLE_EMPTY("ROLE_EMPTY", "Role is empty"),
    EMAIL_EMPTY("EMAIL_EMPTY", "Email is empty"),
    EMAIL_EXISTED("EMAIL_EXISTED", "The email has been existed in the system"),
    USERNAME_EXISTED("USERNAME_EXISTED", "The username has been existed in the system"),
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
