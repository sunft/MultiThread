package com.sunft.base.sync007;

/**
 * 测试volatile关键字的用法
 * @author sunft
 *
 */
public class RunThread extends Thread {
	
	//加volatile关键字
	private volatile boolean isRunning = true;
	
	private void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	@Override
	public void run() {
		System.out.println("进入run方法...");
		//加volatile,每次程序使用isRunning的值
		//都强制线程到共享内存区查找该值
		while(isRunning == true) {
			boolean a = isRunning;
		}
		
		System.out.println("线程停止");
	}

	public static void main(String[] args) 
			throws InterruptedException {
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(3000);
		rt.setRunning(false);
		System.out.println("isRunning的值已经被设置了false");
		Thread.sleep(1000);
		System.out.println(rt.isRunning);
	}
	
}

