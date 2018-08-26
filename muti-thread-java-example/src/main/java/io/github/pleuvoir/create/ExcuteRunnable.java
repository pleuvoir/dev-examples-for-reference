package io.github.pleuvoir.create;

public class ExcuteRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("running...");
	}

	public static void main(String[] args) {
		new Thread(new ExcuteRunnable()).start();
	}
}
