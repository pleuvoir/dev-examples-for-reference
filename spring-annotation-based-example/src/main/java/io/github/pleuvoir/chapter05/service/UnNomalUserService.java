package io.github.pleuvoir.chapter05.service;

public class UnNomalUserService implements UserService{

	@Override
	public void tellMe(String name) {
		System.out.println("我不是普通人[" + name + "]");
	}

}
