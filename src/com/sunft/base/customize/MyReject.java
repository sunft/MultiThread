package com.sunft.base.customize;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义拒绝策略类,需要实现RejectedExecutionHandler接口
 * @author sunft
 *
 */
public class MyReject implements RejectedExecutionHandler {
	
	public MyReject(){}

	/**
	 * 处理任务
	 * 把toString()方法写好,Runnable接口中没有什么方法,
	 * 利用toString()把所有属性表示出来
	 */
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("自定义处理...当前被拒绝任务为：" + r.toString());
	}

}


