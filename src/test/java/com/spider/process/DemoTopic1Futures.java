package com.spider.process;

import org.apache.log4j.Logger;

import com.google.common.util.concurrent.FutureCallback;

public class DemoTopic1Futures implements FutureCallback {
	
	Logger logger = Logger.getLogger(DemoTopic1Futures.class);

	@Override
	public void onSuccess(Object obj) {
		System.out.println("处理成功:" + obj.toString());
		logger.info("sucess");
	}

	@Override
	public void onFailure(Throwable arg0) {
		System.out.println("处理失败");
		logger.info("fail");
	}

}
