package com.sunft.base.sync006;

/**
 * Java中创建对象锁的方式
 * @author sunft
 *
 */
public class ObjectLock {
	
	/**
	 * 对象锁,对同一对象起作用
	 */
	public void method1() {
		synchronized(this) {
			try {
				System.out.println("do method1..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 类锁,对该类的所有对象都会起作用
	 */
	public void method2() {
		synchronized(ObjectLock.class) {
			try {
				System.out.println("do method2..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//任意对象锁,对持有该锁方法起作用
	private Object lock = new Object();
	public void method3() {
		synchronized(lock) {
			try {
				System.out.println("do method3..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		final ObjectLock objLock = new ObjectLock();
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				objLock.method1();
			}
			
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				objLock.method2();
			}
			
		});
		
		Thread t3 = new Thread(new Runnable(){

			@Override
			public void run() {
				objLock.method3();
			}
			
		});
		
		t1.start();
		t2.start();
		t3.start();
		
	}

}
