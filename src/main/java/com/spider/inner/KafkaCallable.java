package com.spider.inner;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import kafka.message.MessageAndMetadata;

public abstract class KafkaCallable<T> implements Callable<T> {

	protected byte[] message;
	protected byte[] key;

	public void setKafkaData(MessageAndMetadata<byte[], byte[]> messageAndMetadata) {
		message = messageAndMetadata.message();
		key = messageAndMetadata.message();
	}

	public abstract T processMessage();

	@Override
	public T call() {
		T t = processMessage();
		//System.out.println("Callable结束");
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return t;
	}

}
