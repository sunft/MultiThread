package com.sunft.base;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


/**
 * 多线程使用Vector或者HashTable的示例(简单线程同步问题)
 * 实现线程同步可使用Vector替代ArrayList, HashTable替代HashMap
 * @author sunft
 *
 */
public class Tickets {

	public static void main(String[] args) {
		
		final Vector<String> tickets = new Vector<String>();
		//封装成线程安全集合
		Map<String, String> map 
			= Collections.synchronizedMap(
					new HashMap<String, String>());
		
		for(int i = 1; i <= 1000; i ++) {
			tickets.add("火车票" + i);
		}
		
		//在迭代的时候调用了remove导致报错,注释掉这行
		//线程部分可以正常执行
		for(Iterator iterator = tickets.iterator(); 
				iterator.hasNext();) {
			String string = (String) iterator.next();
			tickets.remove(20);
		}
		
		//使用10个线程移除票
		for(int i = 0; i <= 10; i ++) {
			new Thread("线程" + i){

				@Override
				public void run() {
					while(true) {
						if(tickets.isEmpty()) break;
						System.out.println(
								Thread.currentThread().getName() 
								+ "---" + tickets.remove(0));
					}
				}
				
			}.start();
		}
	}

}
