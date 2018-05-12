package com.sunft.base.sync005;

/**
 * synchronized的重入
 * @author sunft
 *
 */
public class SyncDubbo2 {
	
	/**
	 * 父类
	 * @author sunft
	 *
	 */
	static class Main{
		
		public int i = 10;
		
		public synchronized void operationSup() {
			try {
				i --;
				System.out.println("Main print i = " + i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 子类
	 * @author sunft
	 *
	 */
	static class Sub extends Main {
		
		public synchronized void operationSub() {
			try {
				while(i > 0) {
					i--;
					System.out.println("Sub print i = " + i);
					Thread.sleep(100);
					//锁重入
					this.operationSup();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				Sub sub = new Sub();
				sub.operationSub();
			}
			
		});
		
		t1.start();
		
	}
	
}


