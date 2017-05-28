package com.sqq.tools.sqlxml.exception;

/**
 * @author Jasun 2017年5月27日 上午12:26:30
 *
 */
public class ParseDocumentException extends RuntimeException {
	private static final long serialVersionUID = -2402671852832752858L;

	public ParseDocumentException(final String errorMessage, final Object... args) {
		super(String.format(errorMessage, args));
	}

	public ParseDocumentException(final Throwable cause) {
		super(cause);
	}
}
