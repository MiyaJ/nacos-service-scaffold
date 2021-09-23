package com.miya.demo.config;

import com.miya.demo.common.enums.ErrorCodeEnum;
import com.miya.demo.common.exception.BusinessException;
import com.miya.demo.model.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * 统一异常处理
 *
 * @author Caixiaowei
 * @date 2021/09/22
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ExceptionAdvice {

    /**
     * 处理请求对象属性不满足校验规则的异常信息
     *
     * @param request
     * @param
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public BaseResponse<Void> exception(HttpServletRequest request, MethodArgumentNotValidException e) {
        String requestUrl = request.getRequestURI();
        // 打印堆栈信息

        String[] str =
                Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split(
                        "\\.");
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String message1 = str[1] + ":" + message;
        log.error("param error:{}", message1);
        return BaseResponse.create(null, 400, message1);
    }

    /**
     * http请求方法不支持
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public BaseResponse<Void> exception(
            HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        String result = exception.getMessage();
        log.warn(result);
        return BaseResponse.create(
                null, ErrorCodeEnum.ERROR_METHOD_NOT_SUPPORT.getStatus(), exception.getMessage());
    }

    /**
     * json错误
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public BaseResponse<Void> exception(
            HttpServletRequest request, HttpMessageNotReadableException exception) {
        String result = exception.getMessage();
        log.warn(result);
        return BaseResponse.create(null, ErrorCodeEnum.ERROR_PARAM.getStatus(), exception.getMessage());
    }

    /**
     * 统一处理业务异常
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BusinessException.class)
    public BaseResponse<Void> exception(HttpServletRequest request, BusinessException exception) {
        log.error("业务异常", exception);
        if (null != exception.getErrorCodeEnum()) {
            log.error(exception.getErrorCodeEnum().getMessage());
            return BaseResponse.createFail(exception.getErrorCodeEnum());
        } else if (null != exception.getMessage() && null != exception.getCode()) {
            log.error(exception.getMessage());
            return BaseResponse.create(null, exception.getCode(), exception.getMessage());
        }
        return BaseResponse.createFailDefault();
    }

    /**
     * 数据库异常
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = DataAccessException.class)
    public BaseResponse<Void> exception(HttpServletRequest request, DataAccessException exception) {
        log.error("数据库异常", exception);
        return BaseResponse.create(null, ErrorCodeEnum.EXCEPT_DB_OPERATION);
    }


    /**
     * Constraint violation exception response entity.
     *
     * @param cx the cx
     * @return the response entity
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse<Object> constraintViolationException(HttpServletRequest request, ConstraintViolationException cx) {
        Set<ConstraintViolation<?>> constraintViolations = cx.getConstraintViolations();
        Optional<ConstraintViolation<?>> constraintViolation = constraintViolations.stream().findFirst();
        if (constraintViolation.isPresent()) {
            String message = constraintViolation.get().getMessage();
            log.error("ConstraintViolationException - > {}", message, cx);
            return BaseResponse.create(null, HttpStatus.BAD_REQUEST.value(), message);
        }
        return BaseResponse.create(null, HttpStatus.BAD_REQUEST.value(), "无法捕获的约束异常");
    }

    /**
     * 统一处理异常
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public BaseResponse<Void> exception(HttpServletRequest request, Exception exception) {
        log.error("系统异常", exception);
        return BaseResponse.create(null, ErrorCodeEnum.SERVICE_EXCEPTION);
    }
}
