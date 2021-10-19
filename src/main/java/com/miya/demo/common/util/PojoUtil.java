package com.miya.demo.common.util;

import cn.hutool.core.bean.BeanUtil;
import com.miya.demo.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public final class PojoUtil {

	public static <S, T> T copy(S source, Class<T> targetClass, String... ignoreProperties) {
		try {
			T instance = targetClass.getDeclaredConstructor().newInstance();
			BeanUtil.copyProperties(source, instance, ignoreProperties);
			return instance;
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	public static <T, R> List<R> copyList(List<T> source, Class<R> targetClazz, String... ignoreProperties) {
		return (List)source.stream().map((s) -> {
			return copy(s, targetClazz, ignoreProperties);
		}).collect(Collectors.toList());
	}

	public static <T, R> List<R> parallelCopyList(List<T> source, Class<R> targetClazz, String... ignoreProperties) {
		return (List)source.parallelStream().map((s) -> {
			return copy(s, targetClazz, ignoreProperties);
		}).collect(Collectors.toList());
	}

	private PojoUtil() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}
}
