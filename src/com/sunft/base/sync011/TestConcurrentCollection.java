package com.sunft.base.sync011;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 测试ConcurrentHashMap
 * @author sunft
 *
 */
public class TestConcurrentCollection {

	public static void main(String[] args) {
		
		ConcurrentMap<String, String> map 
			= new ConcurrentHashMap<String, String>();
		map.put("1", "sunft");
		map.putIfAbsent("1", "3");
		System.out.println(map);
		
		CopyOnWriteArrayList<String> list 
			= new CopyOnWriteArrayList<String>();
		list.add("sunft");
		list.addIfAbsent("sunft");
		System.out.println(list);
		
		//set本身属于不重复集合,因此没上述类似方法
		CopyOnWriteArraySet<String> set 
			= new CopyOnWriteArraySet<String>();
		set.add("sunft");
		set.add("sunft");
		System.out.println(set);
		
		//ConcurrentLinkedQueue也没有实现addIfAbsent
		ConcurrentLinkedQueue<String> queue 
			= new ConcurrentLinkedQueue<String>();
		queue.add("sunft");
		queue.add("sunft");
		System.out.println(queue);
	}

}


