package org.xw.tree;

// 新建Person类实现了Comparable接口
public class Person implements Comparable<Person>{
	// 成全变量
	private int age;
	private String name;
	
	public Person(int age) {
		this.age = age;
	}
	
	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
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
