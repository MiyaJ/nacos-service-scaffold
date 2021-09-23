package com.miya.demo.common.util;

import com.miya.demo.model.base.LoginUser;

/**
 * 用户的安全上下文持有人
 *
 * @author Caixiaowei
 * @date 2021/09/22
 */
public class UserSecurityContextHolder {

	public static boolean exists() {
		return true;
	}

	public static LoginUser getLoginUser() {
		return new LoginUser(1L, "miya");
	}
}
