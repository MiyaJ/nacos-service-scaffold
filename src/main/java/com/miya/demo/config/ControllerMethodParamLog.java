package com.miya.demo.config;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器方法参数日志
 *
 * @author Caixiaowei
 * @date 2021/09/22
 */
@Component
@Aspect
@Slf4j
public class ControllerMethodParamLog {

	@Pointcut("execution(* com.miya.demo.controller..*(..))")
	public void aspect() {
       throw new UnsupportedOperationException();
	}

	@SneakyThrows
	@Around("aspect()")
	public Object before(ProceedingJoinPoint point){
		//获取方法
		ServletRequestAttributes sra =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		assert sra != null;
		HttpServletRequest request = sra.getRequest();
		//访问路径
		String requestPath = request.getRequestURI();
		String className = point.getSignature().getDeclaringType().getSimpleName();
		String methodName = point.getSignature().getName();
		long startTime = System.currentTimeMillis();
		//请求路径,类名,方法名
		String logInfo = StrBuilder.create("\n----------请求路径:").append(requestPath)
				.append("\n-----------类名:").append(className)
				.append("\n-----------方法名:").append(methodName)
				.append("\n-----------参数:").append(getArgs(point)).toString();
		log.info(logInfo);
		Object proceed = point.proceed();
		log.info("\n---------请求结束====返回值: \n" + JSONUtil.toJsonStr(proceed));
		log.info("\n耗时 : " + (System.currentTimeMillis() - startTime));
		return proceed;
	}

	private String getArgs(JoinPoint point) {
		String[] parameterNames = ((MethodSignature) point.getSignature()).getParameterNames();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <parameterNames.length ; i++) {
			sb.append(parameterNames[i]).append(":").append(point.getArgs()[i] == null ? "null" :point.getArgs()[i].toString()).append(";");
		}
		return sb.toString();

	}
}