package com.spider;

import org.junit.Test;

import com.spider.bean.KafkaConsumerConfig;
import com.spider.bean.KafkaConsumerTopicConfig;
import com.spider.process.DemoTopic1Futures;
import com.spider.process.DemoTopic1Process;
import com.spider.util.KafkaConsumerService;

public class KafkaConsumerTestCase {

	@Test
	public void reciver(){
		String zkUrl = "192.168.3.203:2181,192.168.3.205:2181,192.168.3.207:2181";
		String topicId = "paymentCount";
		String groupId = "paymentCount";
		KafkaConsumerService service = new KafkaConsumerService();
		
		KafkaConsumerTopicConfig topicConfig = new KafkaConsumerTopicConfig(topicId, 1, new DemoTopic1Process(), new DemoTopic1Futures());
		KafkaConsumerConfig kafkConfig = new KafkaConsumerConfig(zkUrl, groupId);
		kafkConfig.putKafkaTopicConfig(topicConfig);
		service.startConsumer(kafkConfig, kafkConfig.getTopicConfigMap().values());
		
		try {
			Thread.sleep(1000L*180);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
