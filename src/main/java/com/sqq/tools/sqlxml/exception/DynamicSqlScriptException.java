package com.sqq.tools.sqlxml.exception;

/**
 * @author Jasun 2017年5月27日 上午12:26:30
 *
 */
public class DynamicSqlScriptException extends RuntimeException {
	private static final long serialVersionUID = -2402671852832752848L;

	public DynamicSqlScriptException(final String errorMessage, final Object... args) {
		super(String.format(errorMessage, args));
	}

	public DynamicSqlScriptException(final Throwable cause) {
		super(cause);
	}
}
