package com.spider.bean;

import com.google.common.util.concurrent.FutureCallback;
import com.spider.inner.KafkaCallable;

public class KafkaConsumerTopicConfig {

	private String name;
	private Integer partitionsNum;
	private KafkaCallable processMessage;
	private FutureCallback futureCallback;

	public KafkaConsumerTopicConfig() {
	}

	public KafkaConsumerTopicConfig(String name, Integer partitionsNum, KafkaCallable processMessage,
			FutureCallback futureCallback) {
		this.futureCallback = futureCallback;
		this.name = name;
		this.partitionsNum = partitionsNum;
		this.processMessage = processMessage;
	}

	public KafkaConsumerTopicConfig(String name, Integer partitionsNum, KafkaCallable processMessage) {
		this.name = name;
		this.partitionsNum = partitionsNum;
		this.processMessage = processMessage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPartitionsNum() {
		return partitionsNum;
	}

	public void setPartitionsNum(Integer partitionsNum) {
		this.partitionsNum = partitionsNum;
	}

	public KafkaCallable getProcessMessage() {
		return processMessage;
	}

	public void setProcessMessage(KafkaCallable processMessage) {
		this.processMessage = processMessage;
	}

	public FutureCallback getFutureCallback() {
		return futureCallback;
	}

	public void setFutureCallback(FutureCallback futureCallback) {
		this.futureCallback = futureCallback;
	}

}
