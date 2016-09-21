package com.spider.process;

import org.apache.log4j.Logger;

import com.spider.inner.KafkaCallable;

public class DemoTopic1Process extends KafkaCallable<Object> {
	
	Logger logger = Logger.getLogger(DemoTopic1Process.class);

	@Override
	public Object processMessage() {
		System.out.println("接收到" + new String(message));
		logger.info("接收到" + new String(message));
		return new String(message);
	}

}
