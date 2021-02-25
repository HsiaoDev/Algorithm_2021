package org.xw.tree;

// 新建Person类实现了Comparable接口
public class Person implements Comparable<Person>{
	// 成员变量
	private int age; /// 年龄
	private String name; /// 姓名
	
	// 构造方法
	public Person(int age) {
		this.age = age;
	}
	// 构造方法
	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}
	// 获取年龄
	public int getAge() {
		return age;
	}
	// 设置年龄
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(Person p) {
		return age - p.age;
	}
	
	@Override
	public String toString() {
		return age + "_" + name;
	}

}
