package com.miya.demo;

import com.miya.demo.common.util.MinioUtil;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class Demo1ApplicationTests {

	@Autowired
	private MinioClient minioClient;
	@Autowired
	private MinioUtil minioUtil;

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

}
