package com.mod;
public class Info {

	/** 姓名. */
	private String name;
	/** 性别. */
	private String sex;
	/** 年龄. */
	private String age;

	/**
	 * 设置游客姓名
	 * 
	 * @param name
	 *            姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得游客姓名
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取性别.
	 * 
	 * @return 性别
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置性别.
	 * 
	 * @param sex
	 *            性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获取年龄.
	 * 
	 * @return 年龄
	 */
	public String getAge() {
		return age;
	}

	/**
	 * 设置年龄.
	 * 
	 * @param age
	 *            年龄
	 */
	public void setAge(String age) {
		this.age = age;
	}
}
