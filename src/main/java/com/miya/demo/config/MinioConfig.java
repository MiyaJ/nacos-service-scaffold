package com.miya.demo.config;

import io.minio.MinioClient;
import io.minio.credentials.MinioClientConfigProvider;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio配置
 *
 * @author Caixiaowei
 * @date 2021/10/14
 */
@Configuration
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioConfig {

	/**
	 * 外网地址
	 */
	private String endpoint;

	/**
	 *
	 */
	private String accessKey;

	/**
	 *
	 */
	private String secretKey;

	/**
	 * 内网地址
	 */
	private String endpointIn;

	@Bean
	public MinioClient minioClient() {
		return MinioClient.builder()
				.endpoint(this.endpoint)
				.credentials(this.accessKey, this.secretKey)
				.build();
	}

}
