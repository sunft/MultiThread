package com.sunft.base.sync006;

/**
 * 使用字符串常量作为锁对象,注意String常量池的缓存功能
 * @author sunft
 *
 */
public class StringLock {
	
	public void method() {
		//new String("字符串常量")
		synchronized("字符串常量") {
			while(true) {
				try {
					System.out.println("当前线程:" 
							+ Thread.currentThread().getName() 
							+ "开始");
					Thread.sleep(1000);
					System.out.println("当前线程:" 
							+ Thread.currentThread().getName() 
							+ "结束");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		final StringLock stringLock = new StringLock();
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				stringLock.method();
			}
			
		}, "t1");
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				stringLock.method();
			}
			
		}, "t2");
		
		t1.start();
		t2.start();
	}

}
