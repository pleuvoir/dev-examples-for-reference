package io.github.pleuvoir.exception;

/**
 * 不需要捕获的异常，一般此异常出现时代表程序员写的代码有问题
 * @author pleuvoir
 *
 */
public class NoCatchException extends RuntimeException {

	private static final long serialVersionUID = -5558444914787575591L;

	public NoCatchException() {
		super();
	}

	public NoCatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoCatchException(String message) {
		super(message);
	}

	public NoCatchException(Throwable cause) {
		super(cause);
	}

}
