package com.vtca.color.reader.common.exception;

import com.vtca.color.reader.common.logger.LoggerUtils;
import com.vtca.color.reader.common.Response;
import com.vtca.color.reader.common.Status;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    /**
     * Handle all businesses exception
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Response handleBusinessError(BusinessException e) {
        LoggerUtils.warning(Response.getErrorMessage("BUSINESS"), e);
        e.setStackTrace(new StackTraceElement[]{});
        return Response.failed(e);
    }

    /**
     * Handle for unexpected exception
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response handleUnexpectedError(Exception ex) {
        String message = Response.getErrorMessage("UNEXPECTED");
        LoggerUtils.warning(message, ex);
        BusinessException e = new BusinessException(Status.FAILED.name(), message);
        e.setStackTrace(new StackTraceElement[]{});
        return Response.failed(e);
    }
}
