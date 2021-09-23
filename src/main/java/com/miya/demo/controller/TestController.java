package com.miya.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.miya.demo.entity.Customer;
import com.miya.demo.model.base.BaseResponse;
import com.miya.demo.model.dto.CustomerAddDTO;
import com.miya.demo.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试控制器
 *
 * @author Caixiaowei
 * @date 2021/09/22
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试 API")
public class TestController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public Object list() {
		List<Customer> list = customerService.list();
		return list;
	}

	@ApiOperation(value = "保存客户", notes = "cxw")
	@PostMapping("/saveCustomer")
	public BaseResponse<Object> saveCustomer(@RequestBody @Validated CustomerAddDTO dto) {
		Customer customer = new Customer();
		BeanUtil.copyProperties(dto, customer, CopyOptions.create().ignoreNullValue());
		return BaseResponse.create(customerService.save(customer));
	}

}
