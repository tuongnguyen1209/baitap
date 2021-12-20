package com.vtca.color.reader.common;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.vtca.color.reader.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.Objects;

public class Response extends MappingJacksonValue {

    private static final String MESSAGE_ERROR = "There is some {message} errors. Please contact to admin";

    public static final String getErrorMessage(String error) {
        return Response.MESSAGE_ERROR.replace("{message}", error);
    }

    public Response(Object value) {
        super(value);
    }

    /**
     * response with OK status and response data
     * @param data
     * @return
     */
    public static Response ok(Object data) {
        return buildResponse(data, "data");
    }

    /**
     * response with OK status only
     * @return
     */
    public static Response ok() {
        return buildResponse(null, null);
    }

    /**
     * response with FAILED status and exception cause info
     * @param exception
     * @return
     */
    public static Response failed(BusinessException exception) {
        return buildResponse(exception, "exception");
    }

    /**
     * Build response data from the request
     * @param data
     * @param excludeProp
     * @return
     */
    private static Response buildResponse(Object data, String excludeProp) {

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("status");
        if (Objects.nonNull(excludeProp)) {
            filter = SimpleBeanPropertyFilter.filterOutAllExcept("status", excludeProp);
        }
        FilterProvider filters = new SimpleFilterProvider().addFilter("ResponseMessageFilter", filter);
        Response mapping = null;

        //just return OK status only
        if (Objects.isNull(data)) {
            mapping = new Response(ResponseMessage.ok());
            mapping.setFilters(filters);
            return mapping;
        }

        //return FAILED status with exception data
        if (data instanceof BusinessException) {
            mapping = new Response(ResponseMessage.failed((BusinessException) data));
            mapping.setFilters(filters);
            return mapping;
        }

        //return OK status with response data
        mapping = new Response(ResponseMessage.ok(data));
        mapping.setFilters(filters);

        return mapping;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonFilter("ResponseMessageFilter")
    private static class ResponseMessage {

        private Status status;
        private BusinessException exception;
        private Object data;

        /**
         * Use this constructor in case of success/failed
         * @param status
         * @param data
         */
        private ResponseMessage (Status status, Object data) {
            this.status = status;
            if (data instanceof BusinessException) {
                this.exception = (BusinessException)data;
            } else {
                this.data = data;
            }
        }

        /**
         * Use this constructor in case of error
         * @param exception
         */
        private ResponseMessage (BusinessException exception) {
            this.status = Status.FAILED;
            this.exception = exception;
        }

        /**
         * Use this constructor in case of success
         * @param data
         */
        private ResponseMessage (Object data) {
            this.status = Status.OK;
            this.data = data;
        }

        private ResponseMessage (Status status) {
            this.status = status;
        }

        private static ResponseMessage ok(Object data) {
            return new ResponseMessage(data);
        }

        private static ResponseMessage ok() {
            return new ResponseMessage(Status.OK);
        }

        private static ResponseMessage failed(BusinessException exception) {
            return new ResponseMessage(exception);
        }

        private static ResponseMessage responseWithStatus(Status status, Object data) {
            return new ResponseMessage(status, data);
        }
    }
}
