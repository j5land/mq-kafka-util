package com.spider.bean;

public class KafkaProducerConfig {

	private String brokerList;
	private String serializerClass = "kafka.serializer.DefaultEncoder";
	private String keySerializerClass = "kafka.serializer.StringEncoder";
	private String partitionerClass = "com.spider.util.SimplePartitioner";
	private String requiredAcks = "0";

	public KafkaProducerConfig() {
	}

	public KafkaProducerConfig(String brokerList) {
		this.brokerList = brokerList;
	}

	public String getBrokerList() {
		return brokerList;
	}

	public void setBrokerList(String brokerList) {
		this.brokerList = brokerList;
	}

	public String getSerializerClass() {
		return serializerClass;
	}

	public void setSerializerClass(String serializerClass) {
		this.serializerClass = serializerClass;
	}

	public String getKeySerializerClass() {
		return keySerializerClass;
	}

	public void setKeySerializerClass(String keySerializerClass) {
		this.keySerializerClass = keySerializerClass;
	}

	public String getPartitionerClass() {
		return partitionerClass;
	}

	public void setPartitionerClass(String partitionerClass) {
		this.partitionerClass = partitionerClass;
	}

	public String getRequiredAcks() {
		return requiredAcks;
	}

	public void setRequiredAcks(String requiredAcks) {
		this.requiredAcks = requiredAcks;
	}

}
