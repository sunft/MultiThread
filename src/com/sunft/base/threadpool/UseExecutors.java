package com.sunft.base.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseExecutors {

	public static void main(String[] args) {
		ExecutorService pool 
			= Executors.newFixedThreadPool(5);
	}

}
