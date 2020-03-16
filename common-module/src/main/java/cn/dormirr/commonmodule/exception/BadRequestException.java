package cn.dormirr.commonmodule.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 统一异常处理
 *
 * @author ZhangTianCi
 */
@Getter
public class BadRequestException extends RuntimeException {

    private String status = BAD_REQUEST.getReasonPhrase();

    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(HttpStatus status, String msg) {
        super(msg);
        this.status = status.getReasonPhrase();
    }
}
