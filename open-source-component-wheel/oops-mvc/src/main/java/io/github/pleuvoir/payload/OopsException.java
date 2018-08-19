package io.github.pleuvoir.payload;

public class OopsException extends RuntimeException {

	private static final long serialVersionUID = 2975194473475881394L;

	public String message;

	public OopsException(String message) {
		super();
		this.message = message;
	}

	public OopsException() {
		super();
	}

	public OopsException(String message, Throwable cause) {
		super(message, cause);
	}

	public OopsException(Throwable fillInStackTrace) {
		super(fillInStackTrace);
	}
	
}
