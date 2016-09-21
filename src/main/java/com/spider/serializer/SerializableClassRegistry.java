/**
 * Copyright (c) 2015, Shanghai World Fund Co.,Ltd All Rights Reserved.
 *
 * 包路径:cn.com.spider.mq.kafka.serializer.kryo
 *
 * 当前类名称:SerializableClassRegistry.java
 *
 * @author   wanguohui
 *  
 *    2015~2016 上海万丰文化传播有限公司-版权所有
 *
 */
package com.spider.serializer;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 序列化注册类
 *
 * @author wanguohui
 *
 *         2015年11月28日 下午9:01:22
 * 
 */
public abstract class SerializableClassRegistry {

	private static final Set<Class<?>> registrations = new LinkedHashSet<Class<?>>();

	public static void registerClass(Class<?> clazz) {
		registrations.add(clazz);
	}

	public static Set<Class<?>> getRegisteredClasses() {
		return registrations;
	}
}
