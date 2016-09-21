/**
 * Copyright (c) 2015, Shanghai World Fund Co.,Ltd All Rights Reserved.
 *
 * 包路径:cn.com.spider.mq.kafka.serializer.kryo
 *
 * 当前类名称:MessageKryoSerializer.java
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
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;

/**
 * kryo编解码器
 *
 * @author wanguohui
 *
 *         2015年11月10日 上午10:25:21
 *
 */
public class KryoSerializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(KryoSerializer.class);

	public synchronized static byte[] serialize(Kryo kryo, Object object) {
		LOGGER.info("serialize beginning...");
		Preconditions.checkNotNull(kryo);
		Preconditions.checkNotNull(object);
		Output output = new Output(1, 4096);
		kryo.writeObject(output, object);
		return output.toBytes();

	}

	public synchronized static <T> T deserialize(Kryo kryo, byte[] bytes, Class<T> resultClass) {
		LOGGER.info("deserialize beginning...");
		Preconditions.checkNotNull(kryo);
		Preconditions.checkNotNull(bytes);
		Preconditions.checkNotNull(resultClass);
		Input input = new Input(bytes);

		return kryo.readObject(input, resultClass);

	}
}
