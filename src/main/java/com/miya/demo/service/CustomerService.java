package com.miya.demo.service;

import com.miya.demo.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CustomerService extends IService<Customer>{


	void uploadExcel(MultipartFile file) throws IOException;

	void downloadExcel();

}
