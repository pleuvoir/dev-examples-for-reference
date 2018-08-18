package io.github.pleuvoir.exception;

import java.util.Random;

public class Client {

	public static void main(String[] args) throws CatchException {
		int random = new Random().nextInt(10);
		if (random > 5) {
			// 继承了Exception的异常外层必须处理
			throw new CatchException("受检异常");
		} else {
			// 在实际的开发中  因为Spring事务的原因 业务异常一般都继承了 RuntimeException，外层无法感知所以这种异常得显式处理
			throw new NoCatchException("非受检异常");
		}
	}
}
