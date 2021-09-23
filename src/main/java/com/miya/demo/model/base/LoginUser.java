package com.miya.demo.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录用户
 *
 * @author Caixiaowei
 * @date 2021/09/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements Serializable {

	/**
	 * 登录用户id
	 */
	private Long id;

	/**
	 * 登录用户姓名
	 */
	private String name;

}

