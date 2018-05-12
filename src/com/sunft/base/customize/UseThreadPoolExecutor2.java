package com.sunft.base.customize;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 自定义线程池
 * @author sunft
 *
 */
public class UseThreadPoolExecutor2 implements Runnable {
	
	private static AtomicInteger count 
		= new AtomicInteger();
	
	@Override
	public void run() {
		try {
			int temp = count.incrementAndGet();
			System.out.println("任务" + temp);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		/**
		 * 与有界队列相比，除非系统资源耗尽，否则无界的任务队列不存在入队失败的情况。
		 * 当有新任务到来，系统的线程数小于corePoolSize时，则新建线程执行任务。
		 * 当达到corePoolSize后，就不会继续增加。
		 * 若后续仍有新的任务加入，而有没有空闲的线程资源，则任务直接进入队列等待。
		 * 若任务创建和处理的速度差异很大，无界队列会保持快速增长，直到耗尽系统内存。
		 */
		BlockingQueue<Runnable> queue = 
				new LinkedBlockingQueue<Runnable>();
		
		ExecutorService executor = new ThreadPoolExecutor(
				5,              //core
				10,             //max,这里的10没有意义
				120L,           //2分钟
				TimeUnit.SECONDS,
				queue);
		
		for(int i = 0; i < 20; i ++) {
			executor.execute(new UseThreadPoolExecutor2());
		}
		
		Thread.sleep(1000);
		System.out.println("queue size:" + queue.size());   //10
		Thread.sleep(2000);
		
	}

}






