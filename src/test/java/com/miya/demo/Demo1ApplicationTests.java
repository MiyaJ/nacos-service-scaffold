package com.miya.demo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.miya.demo.common.util.MinioUtil;
import com.miya.demo.entity.Customer;
import com.miya.demo.service.CustomerService;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@SpringBootTest
@Slf4j
class Demo1ApplicationTests {

	@Autowired
	private MinioClient minioClient;
	@Autowired
	private MinioUtil minioUtil;
	@Autowired
	private CustomerService customerService;

	@Test
	void contextLoads() {
	}

	@Test
	public void test() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
		MakeBucketArgs build = MakeBucketArgs.builder()
				.bucket("demo")
				.build();
		minioClient.makeBucket(build);

	}

	@Test
	public void insert1() {
		List<Customer> data = this.createData();
		int maxSize = 1000;

		long begin = System.currentTimeMillis();
		while (data.size() > maxSize) {
			List<Customer> subList = data.subList(0, maxSize);
			data = data.subList(subList.size(), data.size());
			customerService.saveBatch(subList);
		}
		if (CollUtil.isNotEmpty(data)) {
			customerService.saveBatch(data);
		}
		long end = System.currentTimeMillis();

		log.info("耗时2: {}", end - begin);
	}

	private List<Customer> createData() {
		List<Customer> list = Lists.newArrayList();

		for (int i = 0; i < 10000; i++) {
			Customer customer = new Customer();
			customer.setName("cai" + i);
			customer.setEmail(customer.getName() + "@qq.com");
			customer.setDeleteStatus(Boolean.FALSE);
			list.add(customer);
		}
		return list;
	}


	@Autowired
	private RestClient restClient;

	@Test
	public void testElastic() throws IOException {
		Request request = new Request("GET", "/");
		Response response = restClient.performRequest(request);
		log.info("response---> {}", JSONUtil.toJsonStr(response));
	}
}
