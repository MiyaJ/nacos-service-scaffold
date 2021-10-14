package com.miya.demo.service.impl;

import com.miya.demo.service.MinioService;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * impl minio服务
 *
 * @author Caixiaowei
 * @date 2021/10/14
 */
public class MinioServiceImpl implements MinioService {

	@Autowired
	private MinioClient minioClient;


}
