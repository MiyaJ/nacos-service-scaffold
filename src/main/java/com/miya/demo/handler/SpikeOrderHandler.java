package com.miya.demo.handler;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 秒杀订单处理程序
 *
 * @author Caixiaowei
 * @date 2021/12/13
 */
@Component
public class SpikeOrderHandler extends Handler {

	public SpikeOrderHandler() {
		this.orderType = 1;
	}

	@Override
	public void handle(Map<String, Object> orderParamMap) {
		System.out.println("抢购订单处理...");
	}
}
