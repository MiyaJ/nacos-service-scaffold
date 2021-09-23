package com.miya.demo.model.base;

import com.miya.demo.common.enums.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.slf4j.MDC;

import java.io.Serializable;

/**
 * 公共返回类
 *
 * @author Caixiaowei
 * @date 2021/09/22
 */
@Data
@ToString
@ApiModel(value = "公共返回类")
public class BaseResponse<T> implements Serializable {

	private static final long serialVersionUID = 4192965340654225152L;

	@ApiModelProperty(value = "返回码: 0000成功, 其他失败")
	private Integer code;

	@ApiModelProperty(value = "错误信息")
	private String message;

	@ApiModelProperty(value = "返回内容")
	private T data;

	@ApiModelProperty(value = "当前时间戳")
	private Long timeStamp;

	@ApiModelProperty(value = "traceId")
	private String traceId;

	public BaseResponse() {
		this.timeStamp = System.currentTimeMillis();
	}

	public BaseResponse(T data) {
		this(ErrorCodeEnum.SUCCESS, data);
	}

	public BaseResponse(Integer code, String message) {
		this(code, message, null);
	}

	public BaseResponse(Integer code, String message, T data, Long timeStamp) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.timeStamp = timeStamp;
		this.traceId = (String) MDC.get("traceId");
	}

	public BaseResponse(ErrorCodeEnum errorCodeEnum, T data) {
		this(errorCodeEnum.getStatus(), errorCodeEnum.getMessage(), data);
	}

	public BaseResponse(ErrorCodeEnum errorCodeEnum) {
		this(errorCodeEnum, null);
	}

	public BaseResponse(Integer code, String message, T data) {
		this(code, message, data, System.currentTimeMillis());
	}

	public static <T> BaseResponse<T> create(){
		return create(null);
	}

	public static <T> BaseResponse<T> create(T t){
		return new BaseResponse<T>(t);
	}

	public static <T> BaseResponse<T> create(T t, ErrorCodeEnum errorCodeEnum){
		return new BaseResponse<T>(errorCodeEnum.getStatus(), errorCodeEnum.getMessage(), t);
	}

	public static <T> BaseResponse<T> create(T t, Integer code, String msg){
		return new BaseResponse<T>(code, msg, t);
	}

	public static <T> BaseResponse<T> createFail(T t, String msg){
		return new BaseResponse<T>(ErrorCodeEnum.FAIL.getStatus(), msg, t);
	}
	public static <T> BaseResponse<T> createFailDefault(){
		return new BaseResponse<T>(ErrorCodeEnum.FAIL);
	}
	public static <T> BaseResponse<T> createFail(ErrorCodeEnum errorCodeEnum){
		return new BaseResponse<T>(errorCodeEnum.getStatus(), errorCodeEnum.getMessage());
	}
	public static <T> BaseResponse<T> createFail(ErrorCodeEnum errorCodeEnum, String message){
		return new BaseResponse<T>(errorCodeEnum.getStatus(), message);
	}
	public static <T> BaseResponse<T> createFail(String message){
		return new BaseResponse<T>(ErrorCodeEnum.FAIL.getStatus(), message);
	}

}
