package com.sunft.base.customize;

/**
 * »ŒŒÒ¿‡
 * @author sunft
 *
 */
public class MyTask implements Runnable {
	
	private int takdId;
	private String taskName;
	
	public MyTask(int taskId, String taskName) {
		this.takdId = taskId;
		this.taskName = taskName;
	}

	public int getTakdId() {
		return takdId;
	}

	public void setTakdId(int takdId) {
		this.takdId = takdId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public void run() {
		try {
			System.out.println("run takeId = " + this.takdId);
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return Integer.toString(this.takdId);
	}
}




