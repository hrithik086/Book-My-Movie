package com.movie.management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.movie.management.model.Movie;
import com.movie.management.model.Ticket;
import com.movie.management.repo.MovieRepository;
import com.movie.management.repo.TicketRepository;

@SpringBootTest
public class TicketServiceImplTest {
	
	@Autowired
	private TicketServices ticketService;
	
	@MockBean
	private MovieRepository movieRepository;
	@MockBean
	private TicketRepository ticketRepository;
	
	private Ticket ticket;
	private Movie movie;
	private Set<Ticket> ticketSet;
	
	@BeforeEach
	public void setup() {
		ticketSet=new HashSet<Ticket>();
		
		ticket=new Ticket();
		ticket.setTicketId(0);
		ticket.setMovieName("movie name");
		ticket.setTheatreName("theatre name");
		ticket.setNoOfSeats(2);
		
		ticketSet.add(ticket);
		
		movie=new Movie();
		movie.setId(0);
		movie.setMovieName("movie name");
		movie.setTheatreName("theatre name");
		movie.setTicketsAvailable(100);
		movie.setTickets(ticketSet);
		
		ticket.setMovie(movie);
		
		
		when(ticketRepository.findById(0)).thenReturn(Optional.empty());
		when(ticketRepository.save(ticket)).thenReturn(ticket);
		when(movieRepository.findById(0)).thenReturn(Optional.of(movie));
		when(ticketRepository.findById(0)).thenReturn(Optional.of(ticket));
		when(movieRepository.save(movie)).thenReturn(movie);
	}
	
//	@Test
//	public void bookTicketTest() {
//		ticket=new Ticket();
//		int returnedValue=ticketService.bookTickets(0, "movie Name", "theatre name", 2);
//		assertEquals(0, returnedValue);
//		assert
//	}

	@Test
	public void cancelTicketTest() {
		int ticketId=0;
		boolean cancelStatus=ticketService.cancelTickets(ticketId);
		assertEquals(true, cancelStatus);
	}
	
	
}