package com.miya.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 客户上传dto
 *
 * @author Caixiaowei
 * @date 2021/10/18
 */
@Data
public class CustomerUploadDTO implements Serializable {

	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "客户名称")
	@NotEmpty(message = "客户名称不能为空")
	private String name;

	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "客户邮箱")
	@Email(regexp = ".+@.+", message = "客户邮箱格式不正确")
	private String email;
}
