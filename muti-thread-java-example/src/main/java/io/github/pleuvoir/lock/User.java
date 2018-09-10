package io.github.pleuvoir.lock;

public class User {

	private int age;

	public User(int age) {
		super();
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void change() {
		age -= age;
	}
}
