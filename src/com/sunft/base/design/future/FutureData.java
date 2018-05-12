package com.sunft.base.design.future;

/**
 * 相当于代理类
 * @author sunft
 *
 */
public class FutureData implements Data {
	
	//真实数据对象
	private RealData realData;
	
	private boolean isReady = false;
	
	public synchronized void setRealData(RealData realData) {
		//如果已经装载完毕了,就直接返回
		if(isReady) {
			return;
		}
		
		//如果没装载,进行转载真实对象
		this.realData = realData;
		isReady = true;
		//通知下面一个方法的请求
		notify();
	}
	
	@Override
	public synchronized String getRequest() {
		//如果没有装载好,程序就一直处于阻塞状态
		while(!isReady) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//装载好直接获取数据即可
		return this.realData.getRequest();
	}

}


