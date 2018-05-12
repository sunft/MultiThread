package com.sunft.base.sync011;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 分段封装ConcurrentHashMap
 * @author sunft
 *
 */
public class MyConcurrentHashMap {

	/**
	 * 利用该容器再次进行封装
	 */
	private ConcurrentHashMap<String, ConcurrentHashMap<Integer, String>>
	 map = new ConcurrentHashMap<>();
	
}
