package com.vtca.color.reader.common.logger;

import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

@UtilityClass
@Log
public class LoggerUtils {
    private static final String LINE = "-Line: ";
    private static final String MSG_PATTERN = " [Message: {0} with data: {1}]";
    private static final String NO_STACK_TRACE_ELE = "[No StackTraceElement Info]";
    private static final int CLASS_CALLING_LOG_INDEX = 3;//stack trace index of calling log method
    private static final String COMMA = ",";
    private static final String COLON = ":";
    private static final String SQUARE_BRACKETS_LEFT = "[";
    private static final String SQUARE_BRACKETS_RIGHT = "]";
    private static final String PARENTHESIS_LEFT = "(";
    private static final String PARENTHESIS_RIGHT = ")";
    private static final String BULLET = ".";

    public void info(String message, Object... params) {
        log(Level.INFO, message, params);
    }

    public void warning(String message, Object... params) {
        log(Level.WARNING, message, params);
    }

    public void severe(String message, Object... params) {
        log(Level.SEVERE, message, params);
    }

    /**
     * Build common logger to app into partner-core
     */
    private void log(Level logLevel, String message, Object... params) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = null;
        //noinspection ConstantConditions
        if (Objects.nonNull(stackTrace)) {
            stackTraceElement = stackTrace[CLASS_CALLING_LOG_INDEX];
        }
        StringBuilder sbMessagePattern = new StringBuilder(NO_STACK_TRACE_ELE);
        if (Objects.nonNull(stackTraceElement)) {
            sbMessagePattern.setLength(0);
            sbMessagePattern.append(SQUARE_BRACKETS_LEFT);
            sbMessagePattern.append(stackTraceElement.getClassName());
            sbMessagePattern.append(BULLET);
            sbMessagePattern.append(stackTraceElement.getMethodName());
            sbMessagePattern.append(LINE);
            sbMessagePattern.append(stackTraceElement.getLineNumber());
            sbMessagePattern.append(SQUARE_BRACKETS_RIGHT);
        }
        sbMessagePattern.append(MSG_PATTERN);

        final List<String> spParams = new ArrayList<>();
        //convert array params to list object
        Arrays.asList(params).forEach((value) -> spParams.add(String.valueOf(value)));

        StringBuilder sbResult = new StringBuilder();
        //add ( and ) into param String
        if (!spParams.isEmpty()) {
            sbResult.append(PARENTHESIS_LEFT);
            sbResult.append(String.join(COMMA, spParams));
            sbResult.append(PARENTHESIS_RIGHT);
        }

        log.log(logLevel, sbMessagePattern.toString(), new Object[]{message, sbResult.toString()});
    }
}
