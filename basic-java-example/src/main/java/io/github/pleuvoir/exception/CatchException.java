package io.github.pleuvoir.exception;

/**
 * 必须要捕获的异常
 * @author pleuvoir
 *
 */
public class CatchException extends Exception {

	private static final long serialVersionUID = 4417212555284324620L;

	public CatchException() {
		super();
	}

	public CatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public CatchException(String message) {
		super(message);
	}

	public CatchException(Throwable cause) {
		super(cause);
	}

}
