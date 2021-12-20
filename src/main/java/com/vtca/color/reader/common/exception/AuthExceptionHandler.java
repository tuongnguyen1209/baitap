package com.vtca.color.reader.common.exception;

import com.vtca.color.reader.common.logger.LoggerUtils;
import com.vtca.color.reader.common.Response;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class AuthExceptionHandler {

    /**
     * Handle for authentication exception
     * @param ex
     * @return
     */
    @ExceptionHandler({
            DisabledException.class,
            BadCredentialsException.class,
            UsernameNotFoundException.class})
    public Response handleAuthenticationError(Exception ex) {
        LoggerUtils.warning(Response.getErrorMessage("AUTHENTICATION"), ex);
        String code = "", message = "";
        if (ex instanceof DisabledException) {
            code = "USER_DISABLED";
            message = "User is disabled";
        } else if (ex instanceof BadCredentialsException) {
            code = "INVALID_CREDENTIALS";
            message = "Invalid credentials";
        }
        BusinessException e = new BusinessException(code, message);
        e.setStackTrace(new StackTraceElement[]{});

        return Response.failed(e);
    }
}
