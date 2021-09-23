package com.miya.demo.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

/**
 * 错误代码枚举
 *
 * @author Caixiaowei
 * @date 2021/09/04
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    /**
     * Success error code enum.
     */
    SUCCESS(200, "执行成功"),
    /**
     * Fail error code enum.
     */
    FAIL(999, "服务异常"),
    /**
     * Error param error code enum.
     */
    ERROR_PARAM(771, "参数错误"),
    /**
     * Except db operation error code enum.
     */
    EXCEPT_DB_OPERATION(774, "数据库操作异常"),
    /**
     * Service exception error code enum.
     */
    SERVICE_EXCEPTION(775, "服务异常"),
    /**
     * Error method not support error code enum.
     */
    ERROR_METHOD_NOT_SUPPORT(779, "HTTP请求方法不支持"),
    /**
     * 调用第三方服务异常
     */
    THIRD_SERVICE_ERROR(1001, "第三方服务异常"),

    EXCEL_EXPORT_ERROR(10000, "Excel导出失败"),


    ;


    private final Integer status;

    private final String message;


    private static final Set<ErrorCodeEnum> ALL = EnumSet.allOf(ErrorCodeEnum.class);

}
