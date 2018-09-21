package io.github.pleuvoir.chapter5.service;

public class NomalUserService implements UserService{

	@Override
	public void tellMe(String name) {
		System.out.println("我是普通人[" + name + "]");
	}

}
