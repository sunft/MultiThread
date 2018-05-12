package com.sunft.base.sync009;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟阻塞队列
 * @author sunft
 *
 */
public class MyQueue {

	//1、需要一个承载元素的集合
	private LinkedList<Object> list = new LinkedList<Object>();
	
	//2、需要一个计数器
	private AtomicInteger count = new AtomicInteger(0);
	
	//3、需要制定上限和下限
	private final int minSize = 0;
	
	//该变量在构造器中初始化
	private final int maxSize;
	
	//4、构造方法
	public MyQueue(int size) {
		this.maxSize = size;
	}
	
	//5、初始化一个对象用于加锁
	private final Object lock = new Object();
	
	//put(anObject):把anObject加到BlockingQueue里面,
	//如果BlockingQueue没有空间,则调用此方法的线程被阻断,
	//直到BlockingQueue里面有空间为止
	public void put(Object obj) {
		synchronized(lock){
			while(count.get() == this.maxSize) {
				try {
					//阻塞
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//1、加入元素
			list.add(obj);
			//2、计数器累加
			count.incrementAndGet();
			//3、通知另外一个线程(唤醒)
			lock.notify();
			System.out.println("新加入的元素为:" + obj);
		}
	}
	
	//take:取走BlockingQueue里排在首位的对象,若BlockingQueue为空,
	//阻断进入等待状态直到BlockingQueue有新的数据被加入
	public Object take() {
		Object ret = null;
		synchronized(lock) {
			while(count.get() == this.minSize) {
				try {
					//阻塞
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//1、做移除元素操作
			ret = list.removeFirst();
			//2、计数器递减
			count.decrementAndGet();
			//3、唤醒另外一个线程
			lock.notify();
		}
		return ret;
	}
	
	public int getSize() {
		return this.count.get();
	}
	
	public static void main(String[] args) {
		final MyQueue mq = new MyQueue(5);
		mq.put("a");
		mq.put("b");
		mq.put("c");
		mq.put("d");
		mq.put("e");
		
		System.out.println("当前容器的长度:" + mq.getSize());
		
		//向队列中存放数据
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				mq.put("f");
				mq.put("g");
			}
			
		}, "t1");
		
		t1.start();
		
		//从队列中拿出数据
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				Object o1 = mq.take();
				System.out.println("移除的元素为:" + o1);
				Object o2 = mq.take();
				System.out.println("移除的元素为:" + o2);
			}
			
		}, "t2");
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t2.start();
	}
}


