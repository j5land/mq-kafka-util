package com.spider.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.spider.bean.KafkaConsumerConfig;
import com.spider.bean.KafkaConsumerTopicConfig;
import com.spider.inner.KafkaCallable;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

@Service
public class KafkaConsumerService {

	private ConsumerConnector connector;
	private volatile static ListeningExecutorService kafkaThreadService = MoreExecutors
			.listeningDecorator(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1));

	public void startConsumer(KafkaConsumerConfig config, final Collection<KafkaConsumerTopicConfig> topicConfigList) {
		try {
			Properties props = new Properties();
			if (config.getZkConnectUrl() != null) {
				props.put("zookeeper.connect", config.getZkConnectUrl());
			}
			if (config.getGroupId() != null) {
				props.put("group.id", config.getGroupId());
			}
			props.put("zookeeper.session.timeout.ms", config.getSessionTimeout() + "");
			props.put("zookeeper.sync.time.ms", config.getSyncTime() + "");
			props.put("auto.commit.interval.ms", config.getCommitInterval() + "");
			ConsumerConfig consumerConfig = new ConsumerConfig(props);
			connector = Consumer.createJavaConsumerConnector(consumerConfig);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		for (KafkaConsumerTopicConfig topicConfig : topicConfigList) {
			topicCountMap.put(topicConfig.getName(), topicConfig.getPartitionsNum());
		}
		final Map<String, List<KafkaStream<byte[], byte[]>>> streams = connector.createMessageStreams(topicCountMap);
		for (final KafkaConsumerTopicConfig topicConfig : topicConfigList) {
			// 针对Topic进行线程执行
			kafkaThreadService.execute(new Runnable() {
				@Override
				public void run() {
					List<KafkaStream<byte[], byte[]>> partitions = streams.get(topicConfig.getName());
					// 针对分区进行分线程
					for (final KafkaStream<byte[], byte[]> stream : partitions) {
						kafkaThreadService.execute(new Runnable() {
							@Override
							public void run() {
								ConsumerIterator<byte[], byte[]> iter = stream.iterator();
								KafkaCallable processMessage = topicConfig.getProcessMessage();
								while (iter.hasNext()) {
									MessageAndMetadata<byte[], byte[]> item = iter.next();
									processMessage.setKafkaData(item);
									// 针对消息多线程处理
									ListenableFuture listenableFuture = kafkaThreadService.submit(processMessage);
									if (topicConfig.getFutureCallback() != null) {
										Futures.addCallback(listenableFuture, topicConfig.getFutureCallback());
									}
									try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						});

					}
				}
			});
		}
	}
}
