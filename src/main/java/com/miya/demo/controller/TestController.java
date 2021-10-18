package com.miya.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.miya.demo.common.util.MinioUtil;
import com.miya.demo.entity.Customer;
import com.miya.demo.model.base.BaseResponse;
import com.miya.demo.model.dto.CustomerAddDTO;
import com.miya.demo.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
	@Autowired
	private MinioUtil minioUtil;

	@GetMapping("/list")
	public BaseResponse<List<Customer>> list() {
		return BaseResponse.create(customerService.list());
	}

	@ApiOperation(value = "保存客户", notes = "Corki")
	@PostMapping("/saveCustomer")
	public BaseResponse<Object> saveCustomer(@RequestBody @Validated CustomerAddDTO dto) {
		Customer customer = new Customer();
		BeanUtil.copyProperties(dto, customer, CopyOptions.create().ignoreNullValue());
		return BaseResponse.create(customerService.save(customer));
	}

	@ApiOperation(value = "验证bucket 是否存在", notes = "Corki")
	@GetMapping("/bucketExists")
	public BaseResponse<Boolean> bucketExists(String bucketName) throws Exception {
		boolean b = minioUtil.bucketExistes(bucketName);
		return BaseResponse.create(b);
	}

	@PostMapping("/putObject")
	public BaseResponse<String> putObject(@RequestParam("file") MultipartFile file) throws IOException {
		String objectUrl = minioUtil.putObject("miya", file.getOriginalFilename(), file.getInputStream());
		return BaseResponse.create(objectUrl);
	}

	@ApiOperation(value = "上传Excel", notes = "Corki")
	@PostMapping("/uploadExcel")
	public BaseResponse<Void> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
		customerService.uploadExcel(file);
		return BaseResponse.create();
	}

	@ApiOperation(value = "下载Excel", notes = "Corki")
	@PostMapping("/downloadExcel")
	public void downloadExcel() {
		customerService.downloadExcel();
	}

}
