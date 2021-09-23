package com.miya.demo.common.exception;


import com.miya.demo.common.enums.ErrorCodeEnum;
import lombok.Data;

/**
 * 业务异常
 *
 * @author Caixiaowei
 * @date 2021/09/22
 */
@Data
public class BusinessException extends RuntimeException {

    private Integer code;
    private ErrorCodeEnum errorCodeEnum;
    private String message;

    private static final long serialVersionUID = -8205573723309228090L;

    public BusinessException() {
        super();
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.errorCodeEnum = errorCodeEnum;
        this.code = errorCodeEnum.getStatus();
        this.message = errorCodeEnum.getMessage();
    }

    public BusinessException(String message) {
        super(message);
        this.code = ErrorCodeEnum.FAIL.getStatus();
        this.message = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.code = ErrorCodeEnum.FAIL.getStatus();
        this.message = message;
    }

    public BusinessException(Throwable ex) {
        super(ex);
    }
}
