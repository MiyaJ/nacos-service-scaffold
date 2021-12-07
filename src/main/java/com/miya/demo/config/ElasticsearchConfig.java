package com.miya.demo.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * elasticsearch配置
 *
 * @author Caixiaowei
 * @date 2021/12/07
 */
@Configuration
public class ElasticsearchConfig {

	@Bean
	public RestClient restClient() {
		RestClient restClient = RestClient.builder(
				new HttpHost("localhost", 9200, "http")
		).build();
		return restClient;
	}

}
