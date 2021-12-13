package com.miya.demo.handler;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 团购订单处理程序
 *
 * @author Caixiaowei
 * @date 2021/12/13
 */
@Component
public class GroupBuyOrderHandler extends Handler {

	public GroupBuyOrderHandler() {
		this.orderType = 2;
	}

	@Override
	public void handle(Map<String, Object> orderParamMap) {
		System.out.println("团购订单处理...");
	}
}
