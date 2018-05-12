package com.sunft.base.sync012;

/**
 * 自定义类,用于测试
 * @author sunft
 *
 */
public class Task implements Comparable<Task>{
	
	private Integer id;
	private String name;
	
	public Task() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Task o) {
		return this.id > o.id ? 1 : (this.id < o.id ? -1 : 0);
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + "]";
	}

}
