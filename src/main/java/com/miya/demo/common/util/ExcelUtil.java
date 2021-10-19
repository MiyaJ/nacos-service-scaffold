package com.miya.demo.common.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.miya.demo.common.enums.ErrorCodeEnum;
import com.miya.demo.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * excel 工具类
 *
 * @author Caixiaowei
 * @date 2021/10/19
 */
@Slf4j
@Component
public class ExcelUtil {

	/**
	 * 导出Excel文件流,并写回Response
	 *
	 * @param data       数据
	 * @param titleClazz 标题clazz
	 */
	public static <T> void export(List<T> data, Class<T> titleClazz, String fileName) {
		if (data == null) {
			return;
		}
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes == null) {
			return;
		}
		HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
		if (response == null) {
			return;
		}
		if (StrUtil.isEmpty(fileName)) {
			fileName = String.valueOf(System.currentTimeMillis());
		}
		try (ServletOutputStream outputStream = response.getOutputStream()) {
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");

			response.setCharacterEncoding("utf-8");
			response.setContentType("application/zip");
			response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
			EasyExcel.write(outputStream)
					.useDefaultStyle(false)
					.head(titleClazz)
					.sheet()
					.doWrite(data);
		} catch (IOException e) {
			log.error("Excel导出失败:{}", e.getMessage(), e);
			throw new BusinessException(ErrorCodeEnum.EXCEL_EXPORT_ERROR);
		}
	}

	/**
	 * 导出Excel文件流,并写回Response
	 *
	 * @param data       数据
	 * @param titleClazz 标题clazz
	 * @param response   响应
	 */
	public static <T> void export(List<T> data, Class<T> titleClazz, String fileName, HttpServletResponse response) {
		if (data == null) {
			return;
		}
		if (StrUtil.isEmpty(fileName)) {
			fileName = String.valueOf(System.currentTimeMillis());
		}
		try (ServletOutputStream outputStream = response.getOutputStream()) {
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");

			response.setCharacterEncoding("utf-8");
			response.setContentType("application/zip");
			response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
			EasyExcel.write(outputStream)
					.useDefaultStyle(false)
					.head(titleClazz)
					.sheet()
					.doWrite(data);
		} catch (IOException e) {
			log.error("Excel导出失败:{}", e.getMessage(), e);
			throw new BusinessException(ErrorCodeEnum.EXCEL_EXPORT_ERROR);
		}
	}
}
