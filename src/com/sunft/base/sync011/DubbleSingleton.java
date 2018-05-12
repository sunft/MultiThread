package com.sunft.base.sync011;

/**
 * 双重检测锁单例,极端情况下,
 * 这种情况并不能保证线程安全,所以不建议使用
 * @author sunft
 *
 */
public class DubbleSingleton {
	
	private static DubbleSingleton ds;
	
	/**
	 * 私有的构造器是必须的,否则直接可以在外部new,
	 * 白鹤翔老师讲解的时候忘了加私有构造器
	 */
	private DubbleSingleton() {
		
	}
	
	public static DubbleSingleton getDs() {
		if(ds == null) {
			try {
				//模拟初始化对象的准备时间...
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized(DubbleSingleton.class) {
				if(ds == null) {
					ds = new DubbleSingleton();
				}
			}
		}
		
		return ds;
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println(DubbleSingleton.getDs().hashCode());
			}
			
		}, "t1");
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println(DubbleSingleton.getDs().hashCode());
			}
			
		}, "t2");
		Thread t3 = new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println(DubbleSingleton.getDs().hashCode());
			}
			
		}, "t3");
		
		t1.start();
		t2.start();
		t3.start();
	}
	
}
