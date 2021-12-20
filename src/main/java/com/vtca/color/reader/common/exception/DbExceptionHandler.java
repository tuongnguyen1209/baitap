package com.vtca.color.reader.common.exception;

import com.vtca.color.reader.common.logger.LoggerUtils;
import com.vtca.color.reader.common.Response;
import com.vtca.color.reader.common.Status;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NonUniqueResultException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@ControllerAdvice
@RestController
public class DbExceptionHandler {

    /**
     * Handle for repository error
     * @param ex
     * @return
     */
    @ExceptionHandler({
            SQLException.class,
            DataAccessException.class,
            DataIntegrityViolationException.class,
            BadSqlGrammarException.class,
            NoSuchElementException.class,
            NonUniqueResultException.class
            })
    public Response handleSqlError(Exception ex) {

        String message = Response.getErrorMessage("QUERYING DATABASE");
        LoggerUtils.warning(message, ex);
        BusinessException e = new BusinessException(Status.FAILED.name(), message);
        e.setStackTrace(new StackTraceElement[]{});
        return Response.failed(e);
    }
}
