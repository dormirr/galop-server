package cn.dormirr.commonmodule.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ZhangTianCi
 */
@Data
public class ApiError {

    private String status = "Bad Request";
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public static ApiError error(String message) {
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        return apiError;
    }

    public static ApiError error(String status, String message) {
        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMessage(message);
        return apiError;
    }
}


