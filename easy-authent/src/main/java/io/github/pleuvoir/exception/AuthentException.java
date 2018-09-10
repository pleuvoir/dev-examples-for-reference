package io.github.pleuvoir.exception;

public class AuthentException extends RuntimeException {

	private static final long serialVersionUID = -7414701823641446126L;

	public AuthentException() {
		super();
	}

	public AuthentException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthentException(String message) {
		super(message);
	}

	public AuthentException(Throwable cause) {
		super(cause);
	}

}
