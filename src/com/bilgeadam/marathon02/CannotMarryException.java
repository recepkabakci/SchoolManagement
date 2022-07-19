package com.bilgeadam.marathon02;

public class CannotMarryException extends Exception {
	private static final long serialVersionUID = 1L;

	public CannotMarryException() {
		super("A student may not marry");
	}
	
}
