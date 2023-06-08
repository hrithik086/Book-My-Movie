package com.movie.management.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoMovieFound extends Exception {
	
	private static Logger logger=LoggerFactory.getLogger(NoMovieFound.class);
	
	public NoMovieFound(String msg) {
		super(msg);
		logger.warn(msg);
	}

}
