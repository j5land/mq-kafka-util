/**
 * Copyright (c) 2015, Shanghai World Fund Co.,Ltd All Rights Reserved.
 *
 * 包路径:cn.com.spider.mq.kafka.serializer.kryo
 *
 * 当前类名称:ReflectionUtils.java
 *
 * @author   wanguohui
 *  
 *    2015~2016 上海万丰文化传播有限公司-版权所有
 *
 */
package com.spider.serializer;

/**
 * 反射检测
 *
 * @author wanguohui
 *
 *         2015年11月28日 下午9:07:40
 * 
 */
public abstract class ReflectionUtils {

	public static boolean checkZeroArgConstructor(Class<?> clazz) {
		try {
			clazz.getDeclaredConstructor();
			return true;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}
}
