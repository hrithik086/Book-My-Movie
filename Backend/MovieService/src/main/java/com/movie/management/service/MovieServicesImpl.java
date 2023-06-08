package com.movie.management.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.management.Exception.DuplicateMovieException;
import com.movie.management.Exception.NoMovieFound;
import com.movie.management.model.Movie;
import com.movie.management.model.Ticket;
import com.movie.management.repo.MovieRepository;

@Service
public class MovieServicesImpl implements MovieServices {
	
	private static final Logger logger=LoggerFactory.getLogger(MovieServicesImpl.class);
	
	@Autowired
	MovieRepository movieRepo;

	@Override
	public List<Movie> getAllMovies() {
		List<Movie> movies=movieRepo.findAll();
		return movies;
	}

	@Override
	public boolean addMovie(Movie m) throws DuplicateMovieException {
		
		Optional<Movie> movie=movieRepo.findById(m.getId());
		
		if(movie.isPresent()) {
			throw new DuplicateMovieException("Movie with same id is already present");
		}
		else {
			m.setTickets(new HashSet<Ticket>());
			movieRepo.save(m);
			logger.info("movie saved successful");
			return true;
		}		
	}
	
	public boolean updateMovie(Movie m) throws NoMovieFound {
		Optional<Movie> movie=movieRepo.findById(m.getId());
		
		if(movie.isPresent()) {
			Movie updatedMovie=movie.get();
			updatedMovie.setMovieName(m.getMovieName());
			updatedMovie.setTheatreName(m.getTheatreName());
			updatedMovie.setTicketsAvailable(m.getTicketsAvailable());
			movieRepo.save(updatedMovie);
			logger.info("movie updated successful");
			return true;
		}
		
		throw new NoMovieFound("no movie found with the given movie id");
	}

	@Override
	public boolean deleteMovie(int id) throws NoMovieFound {
		if(movieRepo.findById(id).isEmpty()) {
			logger.info("no movie found with the entered movie id");
			throw new NoMovieFound("no movie found with id= "+id);
		}
		movieRepo.deleteById(id);
		logger.info("deletion successful");
		return true;
	}

	@Override
	public boolean isTicketAvailable(int movieId, int noOfTicketsRequired) {
		Optional<Movie> movie=movieRepo.findById(movieId);
		
		if(movie.isPresent() && movie.get().getTicketsAvailable() >= noOfTicketsRequired) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateAvailableTicket(int movieId, int noOfTickets) {
		Optional<Movie> movie=movieRepo.findById(movieId);
		Movie myMovie=null;
		
		if(movie.isPresent()) {
			myMovie=movie.get();
			myMovie.setTicketsAvailable(myMovie.getTicketsAvailable()-noOfTickets);
			movieRepo.save(myMovie);
			return true;
		}
		
		return false;
	}
	
	
	
	
	
	
	
}
