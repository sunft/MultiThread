package com.sunft.base.sync004;

/**
 * 业务整体需要使用完整的synchronized,保证业务的原子性
 * @author sunft
 *
 */
public class DirtyRead {
	
	private String username = "sunft";
	private String password = "123";
	
	public synchronized void setValue(String username, String password) {
		this.username = username;
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.password = password;
		
		System.out.println(Thread.currentThread().getName() 
				+ "调用setValue最终结果是:username = " + username 
				+ ", password = " + password);
	}
	
	public synchronized void getValue() {
		System.out.println(Thread.currentThread().getName() 
				+ "调用getValue方法得到: username = " 
				+ this.username 
				+ " , password = " + this.password);
	}

	public static void main(String[] args) throws Exception {
		final DirtyRead dr = new DirtyRead();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				dr.setValue("z3", "456");
			}
		});
		
		t1.start();
		
		//主线程
		Thread.sleep(1000);
		dr.getValue();
	}

}
