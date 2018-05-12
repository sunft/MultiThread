package com.sunft.base.sync008;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * wait notify方法,wait释放锁,notify不释放锁
 * @author sunft
 *
 */
public class ListAdd2 {

	private volatile static List<String> list = new ArrayList<String>();
	
	public void add() {
		list.add("sunft");
	}
	
	public int size() {
		return list.size();
	}
	
	public static void main(String[] args) {
		final ListAdd2 list = new ListAdd2();
		
		//1、实例化出来一个lock
		//当使用wait和notify的时候,一定要配合着synchronized关键字去使用
//		final Object lock = new Object();
		
		//用于计时控制
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					//synchronized(lock) {
						for(int i = 0; i < 10; i ++) {
							list.add();
							System.out.println("当前线程:" 
							+ Thread.currentThread().getName() 
							+ "添加了一个元素...");
							Thread.sleep(500);
							if(list.size() == 5) {
								System.out.println("已经发出通知...");
								//发出通知
								countDownLatch.countDown();
								//发出通知,但是并未释放锁,等到当前线程执行完毕,
								//t2才能执行
								//lock.notify();
							}
						}
					//}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}, "t1");
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				//synchronized(lock) {
					if(list.size() != 5) {
						try {
							//释放锁
							//lock.wait();
							//继续往下走
							countDownLatch.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("当前线程:" 
					+ Thread.currentThread().getName() 
					+ "收到通知线程停止...");
					throw new RuntimeException();
				}
			//}
			
		}, "t2");
		
		t2.start();
		//保证t2线程优先启动
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t1.start();
		
	}

}
