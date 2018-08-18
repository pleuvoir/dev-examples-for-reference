package io.github.pleuvoir.annotation;

public class Client {

	public static void main(String[] args) throws ClassNotFoundException {
		Person person = new Person();
		person.show();
		
		Man man = new Man();
		man.show();
	}
}
