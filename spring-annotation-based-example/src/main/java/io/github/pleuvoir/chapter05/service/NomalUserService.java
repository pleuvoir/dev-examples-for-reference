package io.github.pleuvoir.chapter05.service;

public class NomalUserService implements UserService{

	@Override
	public void tellMe(String name) {
		System.out.println("我是普通人[" + name + "]");
	}

}
