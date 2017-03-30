package com.java126.threadpool;

import java.io.Serializable;
import java.util.concurrent.Callable;

public class ThreadPoolTask implements Callable<String>, Serializable{
	
	private static final long serialVersionUID = 1L;

	private Object threadPoolTaskData;
	
	private static int consumeTaskSleepTime = 2000;
	
	public ThreadPoolTask(Object tasks){
		this.threadPoolTaskData = tasks;
	}
	
	@Override
	public synchronized String call() throws Exception {
		System.out.println("开始执行任务： " + threadPoolTaskData);
		String result = "";
		
		try {
			for (int i = 0; i < 100000000; i++) {
				
			}
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			e.printStackTrace();
		}
		
		threadPoolTaskData = null;
		return result;
	}
	
	
}
