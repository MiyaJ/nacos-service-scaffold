package com.miya.demo.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
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

	public static final RequestOptions COMMON_OPTIONS;

	static {
		// RequestOptions类保存了请求的部分，这些部分应该在同一个应用程序中的许多请求之间共享。
		// 创建一个singqleton实例，并在所有请求之间共享它。可以设置请求头之类的一些配置
		RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
//		builder.addHeader("Authorization", "Bearer " + TOKEN);
//		builder.setHttpAsyncResponseConsumerFactory(
//		new HttpAsyncResponseConsumerFactory
//		     .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 *1024));
		COMMON_OPTIONS = builder.build();
	}

	@Bean
	public RestHighLevelClient restClient() {
		RestHighLevelClient restClient = new RestHighLevelClient (
				RestClient.builder(
						new HttpHost("localhost", 9200, "http")
				)
		);
		return restClient;
	}

}
