package com.java126.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class StartTaskTread implements Runnable {

	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	private int i;
	
	public StartTaskTread(ThreadPoolTaskExecutor threadPoolTaskExecutor, int i) {
		this.i = i;
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
	}
	
	public synchronized void run() {
		String task = "task@ " + i;
		System.out.println("创建任务并提交到线程池中：" + task);
		FutureTask<String> futureTask = new FutureTask<String>(new ThreadPoolTask(task));
		threadPoolTaskExecutor.execute(futureTask);
		
		String result = null;
		
		try {
			// 取得结果，同时设置超时执行时间为1s。同样可以用future.get()，不设置执行超时时间取得结果
			result = futureTask.get(1000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			futureTask.cancel(true);
			e.printStackTrace();
		} catch (ExecutionException e) {
			futureTask.cancel(true);
			e.printStackTrace();
		} catch (TimeoutException e) {
			futureTask.cancel(true);
			e.printStackTrace();
		} finally {
			System.out.println("task@" + i + ": result=" + result);
		}
		
	}

}
