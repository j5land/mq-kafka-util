package com.spider;

import org.junit.Test;

import com.spider.bean.KafkaProducerConfig;
import com.spider.util.KafkaProducerService;

public class KafkaProducerTestCase {

	@Test
	public void send(){
		
		KafkaProducerService service = new KafkaProducerService();
		//初始化生产者
		KafkaProducerConfig producerCustConfig = new KafkaProducerConfig("192.168.3.203:9092,192.168.3.205:9092,192.168.3.207:9092");
		service.initKafkaProducer(producerCustConfig);
		int i=1;
		while(true){
			service.sendMessage("paymentCount", null, "{\"data\":{\"payment\":\"ccbpay\"}}");
			System.out.println("send:message:"+(i));
			i++;
			try {
				Thread.sleep(1000L*1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
