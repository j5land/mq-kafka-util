package com.spider.bean;

import java.util.HashMap;
import java.util.Map;

public class KafkaConsumerConfig {

	private String zkConnectUrl;
	private String groupId;
	private Integer processMessagePoolSize = 10;
	private Map<String, KafkaConsumerTopicConfig> topicConfigMap = new HashMap<String, KafkaConsumerTopicConfig>();
	private Integer sessionTimeout = 6000;
	private Integer syncTime = 2000;
	private Integer commitInterval = 60000;

	public KafkaConsumerConfig() {
	}

	public KafkaConsumerConfig(String zkConnectUrl, String groupId) {
		this.zkConnectUrl = zkConnectUrl;
		this.groupId = groupId;
	}

	public String getZkConnectUrl() {
		return zkConnectUrl;
	}

	public void setZkConnectUrl(String zkConnectUrl) {
		this.zkConnectUrl = zkConnectUrl;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Map<String, KafkaConsumerTopicConfig> getTopicConfigMap() {
		return topicConfigMap;
	}

	public void setTopicConfigMap(Map<String, KafkaConsumerTopicConfig> topicConfigMap) {
		this.topicConfigMap = topicConfigMap;
	}

	public Integer getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(Integer sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	public Integer getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(Integer syncTime) {
		this.syncTime = syncTime;
	}

	public Integer getCommitInterval() {
		return commitInterval;
	}

	public void setCommitInterval(Integer commitInterval) {
		this.commitInterval = commitInterval;
	}

	public void putKafkaTopicConfig(KafkaConsumerTopicConfig topicConfig) {
		topicConfigMap.put(topicConfig.getName(), topicConfig);
	}

	public Integer getProcessMessagePoolSize() {
		return processMessagePoolSize;
	}

	public void setProcessMessagePoolSize(Integer processMessagePoolSize) {
		this.processMessagePoolSize = processMessagePoolSize;
	}

}
