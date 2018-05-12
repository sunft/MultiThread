package com.sunft.base.sync011;

/**
 * 静态内部类单例
 * @author sunft
 *
 */
public class StaticInnerSingleton {
	
	private StaticInnerSingleton() {}
	
	private static class InnerSingleton {
		private static StaticInnerSingleton single 
			= new StaticInnerSingleton();
	}
	
	public static StaticInnerSingleton getInstance() {
		return InnerSingleton.single;
	}
	
}
