package com.movie.management.service;

import java.util.List;

import com.movie.management.Exception.DuplicateMovieException;
import com.movie.management.Exception.NoMovieFound;
import com.movie.management.model.Movie;

public interface MovieServices {
	List<Movie> getAllMovies();
	boolean addMovie(Movie m) throws DuplicateMovieException;
	boolean updateMovie(Movie m) throws NoMovieFound;
	boolean deleteMovie(int id) throws NoMovieFound;
	boolean isTicketAvailable(int movieId,int noOfTicketsRequired);
	boolean updateAvailableTicket(int movieId, int noOfTickets);
}
