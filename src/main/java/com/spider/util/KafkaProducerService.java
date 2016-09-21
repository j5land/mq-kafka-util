package com.spider.util;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.esotericsoftware.kryo.Kryo;
import com.google.common.base.Preconditions;
import com.spider.bean.KafkaProducerConfig;
import com.spider.serializer.KryoSerializer;
import com.spider.serializer.PooledKryoFactory;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

@Service
public class KafkaProducerService {

	private KafkaProducerConfig producerCustConfig;
	private static ProducerConfig producerConfig;
	private Producer<String, byte[]> producer;
	private Kryo kryo = null;

	public void initKafkaProducer(KafkaProducerConfig producerCustConfig) {
		this.producerCustConfig = producerCustConfig;
		// 设置基础配置
		Properties props = new Properties();
		props.put("metadata.broker.list", producerCustConfig.getBrokerList());
		props.put("key.serializer.class", producerCustConfig.getKeySerializerClass());
		props.put("serializer.class", producerCustConfig.getSerializerClass());
		props.put("partitioner.class", producerCustConfig.getPartitionerClass());
		// producer接收消息ack的时�?.默认�?0.
		// 0: producer不会等待broker发�?�ack
		// 1: 当leader接收到消息之后发送ack
		// 2: 当所有的follower都同步消息成功后发�?�ack.
		props.put("request.required.acks", producerCustConfig.getRequiredAcks());
		producerConfig = new ProducerConfig(props);

		// 实例化生产�??
		producer = new Producer<String, byte[]>(producerConfig);
	}

	public void sendMessage(String topic, Object message) {
		sendMessage(topic, null, message);
	}

	public void sendMessage(String topic, String key, Object message) {
		Preconditions.checkNotNull(topic, "topic cannot be null");
		Preconditions.checkNotNull(message, "message cannot be null");
		// key
		KeyedMessage<String, byte[]> meyedMessage = null;
		byte[] messageByte = null;
		if (message instanceof String) {
			messageByte = ((String) message).getBytes();
		} else {
			messageByte = (byte[]) message;
		}
		if (StringUtils.isNotBlank(key)) {// 如果key没有设置或�?�设置为空字符串
			// 消息
			meyedMessage = new KeyedMessage<String, byte[]>(topic, messageByte);
		} else {// 已经设置了key
			meyedMessage = new KeyedMessage<String, byte[]>(topic, key, messageByte);
		}
		// 发
		producer.send(meyedMessage);
	}

	public Kryo getKryo() {
		if (kryo == null) {
			PooledKryoFactory pooledKryoFactory = new PooledKryoFactory();
			kryo = pooledKryoFactory.getKryo();
		}
		return kryo;
	}
}
