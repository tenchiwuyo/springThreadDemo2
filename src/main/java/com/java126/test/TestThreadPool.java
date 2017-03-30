package com.java126.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.java126.threadpool.StartTaskTread;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class TestThreadPool extends AbstractJUnit4SpringContextTests {

	private static int produceTaskSleepTime = 10;
	
	private static int produceTaskMaxNumber = 1000;
	
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	@Test
	public void testThreadPoolExecutor(){
		for (int i = 0; i < produceTaskMaxNumber; i++) {
			try {
				Thread.sleep(produceTaskSleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new Thread(new StartTaskTread(threadPoolTaskExecutor, i)).start();
		}
	}
}
