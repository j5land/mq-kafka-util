/**
 * Copyright (c) 2015, Shanghai World Fund Co.,Ltd All Rights Reserved.
 *
 * 包路径:cn.com.spider.mq.kafka.serializer.kryo
 *
 * 当前类名称:PooledKryoFactory.java
 *
 * @author   wanguohui
 *  
 *    2015~2016 上海万丰文化传播有限公司-版权所有
 *
 */
package com.spider.serializer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.esotericsoftware.kryo.Kryo;

/**
 * 池化kryo工厂类
 *
 * @author wanguohui
 *
 *         2015年11月28日 下午9:06:40
 * 
 */
public class PooledKryoFactory extends KryoFactory {

	private final Queue<Kryo> pool = new ConcurrentLinkedQueue<Kryo>();

	@Override
	public void returnKryo(Kryo kryo) {
		pool.offer(kryo);
	}

	@Override
	public void close() {
		pool.clear();
	}

	public Kryo getKryo() {
		Kryo kryo = pool.poll();
		if (kryo == null) {
			kryo = createKryo();
		}
		return kryo;
	}
}
