package com.movie.management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.movie.management.Exception.DuplicateMovieException;
import com.movie.management.Exception.NoMovieFound;
import com.movie.management.model.Movie;
import com.movie.management.model.Ticket;
import com.movie.management.repo.MovieRepository;

@SpringBootTest
public class MovieServiceImplTest {
	
	@Autowired
	private MovieServices movieServices;
	
	@MockBean
	private MovieRepository movieRepo;
	
	private Movie movie;
	private Ticket ticket;
	private Set<Ticket> ticketSet;
	
	@BeforeEach
	public void setup() {
		ticketSet=new HashSet<Ticket>();
		
		movie=new Movie();
		movie.setId(0);
		movie.setMovieName("movie name");
		movie.setTheatreName("theatre name");
		movie.setTicketsAvailable(100);
		
		ticket=new Ticket();
		ticket.setTicketId(0);
		ticket.setMovieName("movie name");
		ticket.setTheatreName("theatre name");
		ticket.setNoOfSeats(2);
		ticket.setMovie(movie);
		
		movie.setTickets(ticketSet);
		
		when(movieRepo.findById(0)).thenReturn(Optional.of(movie));
		when(movieRepo.findById(1)).thenReturn(Optional.empty());
		when(movieRepo.save(movie)).thenReturn(movie);
	}
	
	@Test
	public void addMovieTest() {
		boolean addMovieStatus;
		movie.setId(1);
		try {
			addMovieStatus = movieServices.addMovie(movie);
			assertEquals(true, addMovieStatus);
		} catch (DuplicateMovieException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateMovieTest() {
		try {
			boolean movieUpdateStatus=movieServices.updateMovie(movie);
			assertEquals(true, movieUpdateStatus);
		} catch (NoMovieFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteMovieTest() {
		try {
			boolean deleteMovieStatus=movieServices.deleteMovie(0);
			assertEquals(true, deleteMovieStatus);
		}
		catch(NoMovieFound nmf) {
			nmf.printStackTrace();
		}
	}
	
	@Test
	public void isTickeAvailableTest() {
		boolean ticketAvailableStatus= movieServices.isTicketAvailable(0, 5);
		assertEquals(true, ticketAvailableStatus);
	}
	
	@Test
	public void updateAvailableTicket() {
		boolean updateMovieStatus=movieServices.updateAvailableTicket(0, 5);
		assertEquals(true, updateMovieStatus);
	}
}
