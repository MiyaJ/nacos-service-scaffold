package com.miya.demo.handler;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 处理程序
 *
 * @author Caixiaowei
 * @date 2021/12/13
 */
public abstract class Handler {

	/**
	 * 订单类型, 子类初始化的时候指定
	 */
	protected Integer orderType;

	@Autowired
	private HandlerHolder handlerHolder;

	@PostConstruct
	public void init() {
		handlerHolder.putHandler(orderType, this);
	}

	/**
	 * 做处理
	 */
	public void doHandle(Map<String, Object> orderParamMap) {
		handlerHolder.rout(orderType).handle(orderParamMap);
	}

	/**
	 * 处理
	 */
	public abstract void handle(Map<String, Object> orderParamMap);
}
