package io.github.pleuvoir.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import io.github.pleuvoir.kit.SleepUtil;

public class UserServiceReadWriteLock implements UserService {

	ReadWriteLock rw = new ReentrantReadWriteLock();
	Lock readLock = rw.readLock();
	Lock writeLock = rw.writeLock();

	private User user;

	public UserServiceReadWriteLock(User user) {
		this.user = user;
	}

	@Override
	public User get() {
		readLock.lock();
		try {
			SleepUtil.ms(5);
			return this.user;
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public void update() {
		writeLock.lock();
		try {
			SleepUtil.ms(5);
			this.user.change();
		} finally {
			writeLock.unlock();
		}
	}

}
