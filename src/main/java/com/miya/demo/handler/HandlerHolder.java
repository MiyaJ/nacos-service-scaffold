package com.miya.demo.handler;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理程序持有人
 *
 * @author Caixiaowei
 * @date 2021/12/13
 */
@Component
public class HandlerHolder {

	private Map<Integer, Handler> handlers = new HashMap<>();

	public void putHandler(Integer orderType, Handler handler) {
		this.handlers.put(orderType, handler);
	}

	public Handler rout(Integer orderType) {
		return this.handlers.get(orderType);
	}
}
