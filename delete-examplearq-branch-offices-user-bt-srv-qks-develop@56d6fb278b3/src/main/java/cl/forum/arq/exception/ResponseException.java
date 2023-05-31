package cl.forum.arq.exception;

import java.io.Serializable;

public class ResponseException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public ResponseException() {
	}

	public ResponseException(String message) {
		super(message);
	}

	public ResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResponseException(Throwable cause) {
		super(cause);
	}

	public ResponseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}