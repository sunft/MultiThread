package com.sunft.base.sync007;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试AtomicInteger原子性
 * @author sunft
 *
 */
public class AtomicUse {

	private static AtomicInteger 
		count = new AtomicInteger(0);
	
	/**
	 * 多个addAndGet在一个方法内是非原子性的,
	 * 需要加synchronized进行修饰的,
	 * 保证4个addAndGet整体原子性
	 * @return
	 */
	public synchronized int multiAdd() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//只保证单行代码的原子性,并不保证下面四行的原子性
		count.addAndGet(1);
		count.addAndGet(2);
		count.addAndGet(3);
		count.addAndGet(4);//+10
		
		return count.get();
	}
	
	public static void main(String[] args) {
		final AtomicUse au = new AtomicUse();
		
		List<Thread> ts = new ArrayList<Thread>();
		for(int i = 0; i < 100; i ++) {
			ts.add(new Thread(new Runnable(){

				@Override
				public void run() {
					System.out.println(au.multiAdd());
				}
				
			}));
		}
		
		for(Thread t : ts) {
			t.start();
		}
	}

}



