package com.wenqiuhan.taotao.tools;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;

public class TencentCos {
	// 1 初始化用户身份信息（secretId, secretKey）。
	COSCredentials cred = new BasicCOSCredentials("", "");
	// 2 设置bucket的区域, COS地域的简称请参照
	// https://cloud.tencent.com/document/product/436/6224
	// clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java
	// SDK 部分。
	ClientConfig clientConfig = new ClientConfig(new Region("ap-chengdu"));
	// 3 生成 cos 客户端。
	COSClient cosClient = new COSClient(cred, clientConfig);
	// bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
	String bucketName = "mybucket-1251668577";
}
