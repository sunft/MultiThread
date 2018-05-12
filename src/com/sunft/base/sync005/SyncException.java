package com.sunft.base.sync005;

/**
 * 当程序发生异常,会释放掉占有的锁
 * @author sunft
 *
 */
public class SyncException {
	
	private int i = 0;
	
	/**
	 * 一旦抛出异常,其他线程就可以进入该方法,
	 * 因此在catch块中要做好异常的处理,
	 * 处理业务逻辑时一定要处理好
	 */
	public synchronized void operation() {
		while(true) {
			try {
				i ++;
				Thread.sleep(200);
				System.out.println(
						Thread.currentThread().getName() 
						+ " , i = " + i);
				if(i == 10) {
					//这里会抛运行时异常
					Integer.parseInt("a");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(" log info i = " + i);
				continue;
			} 
		}
	}

	public static void main(String[] args) {
		final SyncException se = new SyncException();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				se.operation();
			}
			
		}, "t1");
		
		t1.start();
	}

}


