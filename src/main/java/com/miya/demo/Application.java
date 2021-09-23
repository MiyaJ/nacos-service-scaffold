package com.miya.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 应用程序应用
 *
 * @author Caixiaowei
 * @date 2021/09/22
 */
@MapperScan("com.miya.demo.mapper")
@SpringBootApplication(scanBasePackages = "com.miya.demo")
@EnableDiscoveryClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
