package com.sunft.base.sync006;

/**
 * 使用对象作为锁,并且改变对象的属性
 * @author sunft
 *
 */
public class ChangeLock3 {
	
	//使用对象作为锁
	private Student lock = new Student("sunft", 18);
	
	/**
	 * 测试改变锁对象
	 */
	private void method() {
		synchronized(lock) {
			try {
				System.out.println("当前线程:" 
						+ Thread.currentThread().getName() 
						+ "开始...");
				//改变对象属性,可以实现同步
				lock.setName("Bruce Lee");
				lock.setAge(20);
				Thread.sleep(2000);
				System.out.println("当前线程:" 
						+ Thread.currentThread().getName() 
						+ "结束...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 锁对象被改变,加不加锁没啥区别
	 * @param args
	 */
	public static void main(String[] args) {
		final ChangeLock3 changeLock = new ChangeLock3();
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				changeLock.method();
			}
			
		}, "t1");
		
		t1.start();
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				changeLock.method();
			}
			
		}, "t2");
		
		t2.start();
	}

}


