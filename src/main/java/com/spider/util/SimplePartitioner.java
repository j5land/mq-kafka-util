/**
 * Copyright (c) 2015, Shanghai World Fund Co.,Ltd All Rights Reserved.
 *
 * 包路径:cn.com.spider.mq.kafka
 *
 * 当前类名称:SimplePartitioner.java
 *
 * @author   wanguohui
 *  
 *    2015~2016 上海万丰文化传播有限公司-版权所有
 *
 */
package com.spider.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * 简单分区类
 *
 * @author wanguohui
 *
 *         2015年11月3日 下午1:38:19
 * 
 */
public class SimplePartitioner implements Partitioner {

	private static final Log LOGGER = LogFactory.getLog(SimplePartitioner.class);
	
	private static final Integer VIRTUAL_NODE_COUNT = 160;//虚拟节点数
	
	final AtomicInteger counter = new AtomicInteger(0);

	public SimplePartitioner(VerifiableProperties props) {
	}

	@Override
	public int partition(Object key, int numPartitions) {
		List<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < numPartitions; i++) {
			nodes.add(new Node("00"+i));
		}
		
		KetamaNodeLocator k = new KetamaNodeLocator(nodes,HashAlgorithm.SPIDER_HASH,VIRTUAL_NODE_COUNT);
		
		Node node=k.getPrimary((String) key);
		int partitionId=new Integer(node.getName().substring(2));
		LOGGER.info("<<<<partitionId>>>>>> "+ partitionId);
		return partitionId; 
	}
	
	
}
