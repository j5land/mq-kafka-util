/**
 * Copyright (c) 2015, Shanghai World Fund Co.,Ltd All Rights Reserved.
 *
 * 包路径:cn.com.spider.mq.kafka.serializer.kryo
 *
 * 当前类名称:HashAlgorithm.java
 *
 * @author   wanguohui
 *  
 *    2015~2016 上海万丰文化传播有限公司-版权所有
 *
 */
package com.spider.util;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash算法类
 *
 * @author wanguohui
 *
 * 2015年11月10日 上午9:25:31
 *
 */
public enum HashAlgorithm {

	/**
	 * MD5-based hash algorithm used by ketama.
	 */
	SPIDER_HASH;

	public long hash(byte[] digest, int nTime) {
		long rv = ((long) (digest[3+nTime*4] & 0xFF) << 24)
				| ((long) (digest[2+nTime*4] & 0xFF) << 16)
				| ((long) (digest[1+nTime*4] & 0xFF) << 8)
				| (digest[0+nTime*4] & 0xFF);
		
		return rv & 0xffffffffL; /* Truncate to 32-bits */
	}

	/**
	 * Get the md5 of the given key.
	 */
	public byte[] computeMd5(String k) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 not supported", e);
		}
		md5.reset();
		byte[] keyBytes = null;
		try {
			keyBytes = k.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unknown string :" + k, e);
		}
		
		md5.update(keyBytes);
		return md5.digest();
	}
}

