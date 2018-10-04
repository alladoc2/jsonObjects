package com.gfs.projects.common.exception;

public class MGSException extends Exception implements MGSExceptionConstant{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2332385513428712957L;

	private String errorCode;
	
	private String errorDescription;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	public MGSException (String message) {
		super(message);
		this.errorCode = ERROR_CODE_UNSPECIFIED;
		this.errorDescription = message;
	}
	
	public MGSException (String code, String message) {
		super(message);
		this.errorCode = code;
		this.errorDescription = message;
	}
	
}
