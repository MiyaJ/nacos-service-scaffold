package com.miya.demo.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * knife4j配置
 *
 * @author Caixiaowei
 * @date 2021/09/22
 */
@Configuration
@EnableOpenApi
@EnableKnife4j
public class Knife4jConfiguration {

	@Bean
	public Docket defaultApi() {
		return new Docket(DocumentationType.OAS_30)
				.enable(true)
				.apiInfo(apiInfo())
				//分组名称
				.groupName("scaffold")
				.select()
				//这里指定Controller扫描包路径
				.apis(RequestHandlerSelectors.basePackage("com.miya.demo.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("swagger-bootstrap-ui-demo RESTful APIs")
				.description("# swagger-bootstrap-ui-demo RESTful APIs")
				.termsOfServiceUrl("http://www.xx.com/")
				.contact(new Contact("cxw", "www.baidu.com", "123456@qq.com"))
				.version("1.0")
				.build();
	}
}
