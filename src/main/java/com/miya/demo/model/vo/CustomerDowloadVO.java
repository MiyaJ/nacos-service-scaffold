package com.miya.demo.model.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 客户dowload VO
 *
 * @author Caixiaowei
 * @date 2021/10/18
 */
@Data
public class CustomerDowloadVO implements Serializable {

	@ExcelProperty(value = "客户名称")
	private String name;

	@ExcelProperty(value = "客户邮箱")
	private String email;

}
