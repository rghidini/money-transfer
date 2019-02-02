package com.ghidini.tm.exceptions;

public class DBCommitException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DBCommitException() {
		super("An error has ocurred trying to commit ");
	}


}
