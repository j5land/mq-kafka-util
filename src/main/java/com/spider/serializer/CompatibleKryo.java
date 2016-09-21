/**
 * Copyright (c) 2015, Shanghai World Fund Co.,Ltd All Rights Reserved.
 *
 * 包路径:cn.com.spider.mq.kafka.serializer.kryo
 *
 * 当前类名称:CompatibleKryo.java
 *
 * @author   wanguohui
 *  
 *    2015~2016 上海万丰文化传播有限公司-版权所有
 *
 */
package com.spider.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;

/**
 * 考虑到兼容性的序列化类
 *
 * @author wanguohui
 *
 *         2015年11月28日 下午9:18:29
 * 
 */
public class CompatibleKryo extends Kryo {

	private static final Logger logger = LoggerFactory.getLogger(CompatibleKryo.class);

	@Override
	public Serializer getDefaultSerializer(Class type) {
		if (type == null) {
			throw new IllegalArgumentException("type cannot be null.");
		}

		if (!type.isArray() && !ReflectionUtils.checkZeroArgConstructor(type)) {
			if (logger.isWarnEnabled()) {
				logger.warn(type + " has no zero-arg constructor and this will affect the serialization performance");
			}
			return new JavaSerializer();
		}
		return super.getDefaultSerializer(type);
	}
}
