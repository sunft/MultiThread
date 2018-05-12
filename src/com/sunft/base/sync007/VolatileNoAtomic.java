package com.sunft.base.sync007;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 验证Volatile的非原子性
 * @author sunft
 *
 */
public class VolatileNoAtomic extends Thread {
	
//	private static volatile int count = 0;
	//单个JVM的环境下,尽量使用原子类保证同步
	private static AtomicInteger count = new AtomicInteger(0);
	
	private static void addCount() {
		for(int i = 0; i < 1000; i ++) {
//			count ++;
			count.incrementAndGet(); //++
		}
		//System.out很浪费性能,尽量避免使用
		System.out.println(count);
	}

	@Override
	public void run() {
		addCount();
	}

	public static void main(String[] args) {
		
		VolatileNoAtomic[] arr = new VolatileNoAtomic[10];
		for(int i = 0; i < 10; i ++) {
			arr[i] = new VolatileNoAtomic();
		}
		
		for(int i = 0; i < 10; i ++) {
			arr[i].start();
		}
	}

}


