package com.example.test.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.helpers.MessageFormatter;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Object... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage());
    }

}
