package com.miya.demo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miya.demo.common.util.ExcelUtil;
import com.miya.demo.common.util.PojoUtil;
import com.miya.demo.entity.Customer;
import com.miya.demo.listener.CustomerUploadListener;
import com.miya.demo.mapper.CustomerMapper;
import com.miya.demo.model.dto.CustomerUploadDTO;
import com.miya.demo.model.vo.CustomerDowloadVO;
import com.miya.demo.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 客户服务impl
 *
 * @author Caixiaowei
 * @date 2021/10/19
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService{

	@Override
	public void uploadExcel(MultipartFile file) throws IOException {
		EasyExcel.read(file.getInputStream(), CustomerUploadDTO.class, new CustomerUploadListener(this)).sheet().doRead();
	}

	@Override
	public void downloadExcel() {
		// 根据条件查询导出数据
		List<Customer> list = this.list();
		List<CustomerDowloadVO> dowloadVOList = PojoUtil.copyList(list, CustomerDowloadVO.class);
		ExcelUtil.export(dowloadVOList, CustomerDowloadVO.class, "客户数据");
	}
}
