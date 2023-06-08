package com.movie.management.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuplicateMovieException extends Exception {
	
	private static final Logger logger=LoggerFactory.getLogger(DuplicateMovieException.class);
	
	public DuplicateMovieException(String msg) {
		super(msg);
		logger.warn(msg);
	}
}
