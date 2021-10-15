package com.miya.demo.common.util;

import cn.hutool.core.util.StrUtil;
import com.miya.demo.config.MinioConfig;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.StringJoiner;

/**
 * minio工具
 *
 * @author Caixiaowei
 * @date 2021/10/15
 */
@Component
@Slf4j
public class MinioUtil {

	@Autowired
	private MinioConfig minioConfig;
	@Autowired
	private MinioClient minioClient;

	/**
	 * 验证桶是否已存在
	 *
	 * @param bucketName bucket名称
	 * @return boolean
	 * @throws Exception 异常
	 */
	public boolean bucketExistes (String bucketName) throws Exception {
		BucketExistsArgs bucketArgs = BucketExistsArgs.builder().bucket(bucketName).build();
		return minioClient.bucketExists(bucketArgs);
	}

	/**
	 * 创建桶
	 *
	 * @param bucket 桶名称
	 * @param region 地区
	 * @return boolean
	 */
	public boolean makeBucket(String bucket, String region) {
		MakeBucketArgs bucketArgs = MakeBucketArgs.builder()
				.bucket(bucket)
				.region(region)
				.build();
		try {
			minioClient.makeBucket(bucketArgs);
		} catch (Exception e) {
			log.error("makeBucket error, bucket: {}, e: {}", bucket, e);
			return false;
		}
		return true;
	}

	/**
	 * 流上传文件
	 *
	 * @param bucket      桶
	 * @param object      对象
	 * @param inputStream 输入流
	 * @return {@link String}
	 */
	public String putObject(String bucket, String object, InputStream inputStream) {
		PutObjectArgs args = PutObjectArgs.builder()
				.bucket(bucket)
				.object(object)
				.stream(inputStream, -1L, 6000000L)
				.build();
		String objectUrl = StrUtil.EMPTY;
		try {
			ObjectWriteResponse response = minioClient.putObject(args);
			objectUrl = this.getObjectUrl(response.bucket(), response.object());
		} catch (Exception e) {
			log.error("putObject error, bucket:{}, object:{}, e", bucket, object, e);
		}
		return objectUrl;
	}

	/**
	 * 得到对象url
	 *
	 * @param bucket 桶
	 * @param object 对象
	 * @return {@link String}
	 */
	public String getObjectUrl(String bucket, String object) {
		StringJoiner sj = new StringJoiner(StrUtil.SLASH);
		sj.add(minioConfig.getEndpoint()).add(bucket).add(object);
		return sj.toString();
	}
}
