package com.sunft.base.design.master;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		//声明20个线程
		Master master = new Master(new Worker(), 20);
		
		Random r = new Random();
		
		//提交100个任务给Master
		for(int i = 1; i <= 100; i ++) {
			Task t = new Task();
			t.setId(i);
			t.setPrice(r.nextInt(1000));
			master.submit(t);
		}
		//启动任务
		master.execute();
		long start = System.currentTimeMillis();
		
		while(true) {
			if(master.isComplete()) {
				long end = System.currentTimeMillis() - start;
				int priceResult = master.getResult();
				System.out.println("最终结果:" 
						+ priceResult + ",执行时间:" + end);
				break;
			}
		}
	}

}


