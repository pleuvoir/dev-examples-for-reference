package io.github.pleuvoir.lock;

import io.github.pleuvoir.kit.SleepUtil;

public class UserServiceSync implements UserService {

	private User user;

	public UserServiceSync(User user) {
		this.user = user;
	}

	@Override
	public synchronized User get() {
		SleepUtil.ms(5);
		return user;
	}

	@Override
	public synchronized void update() {
		SleepUtil.ms(5);
		user.change();
	}

}
