package com.miya.demo.listener;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.miya.demo.entity.Customer;
import com.miya.demo.model.dto.CustomerUploadDTO;
import com.miya.demo.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户上传侦听器
 *
 * @author Caixiaowei
 * @date 2021/10/18
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUploadListener extends AnalysisEventListener<CustomerUploadDTO> {

	private CustomerService customerService;

	private static final int BATCH_COUNT = 5;
	List<Customer> list = new ArrayList<Customer>();

	public CustomerUploadListener(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public void invoke(CustomerUploadDTO customerUploadDTO, AnalysisContext analysisContext) {
		log.info("invoke save customerUploadDTO : {}", JSONUtil.toJsonStr(customerUploadDTO));
		Customer customer = BeanUtil.copyProperties(customerUploadDTO, Customer.class);
		list.add(customer);
		if (list.size() >= BATCH_COUNT) {
			log.info("invoke save data : {}", JSONUtil.toJsonStr(list));
			saveData();
			// 存储完成清理 list
			list.clear();
		}
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
		log.info("doAfterAllAnalysed save data : {}", JSONUtil.toJsonStr(list));
		saveData();
	}

	private void saveData() {
		customerService.saveBatch(list);
	}
}
