package com.sunft.base.design.future;

public class Main {

	public static void main(String[] args) {
		FutureClient fc = new FutureClient();
		Data data = fc.request("请求参数");
		//#代码可以往下走
		System.out.println("请求发送成功");
		System.out.println("做其他的事情...");
		
		//执行完一段代码,最后获取返回结果
		String result = data.getRequest();
		System.out.println(result);
	}

}
