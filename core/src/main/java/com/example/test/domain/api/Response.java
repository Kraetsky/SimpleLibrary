package com.example.test.domain.api;

import com.example.test.enums.Status;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
@Data
public class Response {

    private final Object data;
    private final Status status;
    private final Error error;

    public static Response ok() {
        return new Response(null, Status.OK, null);
    }

    public static Response ok(Object data) {
        return new Response(data, Status.OK, null);
    }

    public static Response fail(String errorMessage) {
        return new Response(null, Status.ERROR, new Error(errorMessage));
    }

    public static Response from(Runnable task) {
        try {
            task.run();
            return ok();
        } catch (Exception ex) {
            log.error("Error occurred while executing a task: {}.", ex.getMessage());
            return fail(ex.getMessage());
        }
    }

    public static <T> Response from(Supplier<T> task) {
        try {
            T result = task.get();
            return ok(result);
        } catch (Exception ex) {
            log.error("Error occurred while executing a task: {}.", ex.getMessage());
            return fail(ex.getMessage());
        }
    }

}
