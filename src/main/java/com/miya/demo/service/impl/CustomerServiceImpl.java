package com.miya.demo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.miya.demo.listener.CustomerUploadListener;
import com.miya.demo.model.dto.CustomerUploadDTO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miya.demo.entity.Customer;
import com.miya.demo.mapper.CustomerMapper;
import com.miya.demo.service.CustomerService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService{

	@Override
	public void uploadExcel(MultipartFile file) throws IOException {
		EasyExcel.read(file.getInputStream(), CustomerUploadDTO.class, new CustomerUploadListener());
	}

	@Override
	public void downloadExcel() {

	}
}
