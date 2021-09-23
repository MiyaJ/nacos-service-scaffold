package com.miya.demo.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页请求参数
 *
 * @author Caixiaowei
 * @date 2021/09/04
 */
@Data
public class PageRequest {

	@ApiModelProperty(name = "当前页数", value = "当前页", required = true, example = "1")
	private Long pageNum = 1L;

	@ApiModelProperty(name = "每页条数", value = "每页数量", required = true, example = "10")
	private Long pageSize = 10L;
}
