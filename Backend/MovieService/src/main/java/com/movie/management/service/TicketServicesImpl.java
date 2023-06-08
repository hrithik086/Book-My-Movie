package com.movie.management.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.management.model.Movie;
import com.movie.management.model.Ticket;
import com.movie.management.repo.MovieRepository;
import com.movie.management.repo.TicketRepository;

@Service
public class TicketServicesImpl implements TicketServices {
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	MovieRepository movieRepository;

	@Override
	public int bookTickets(int movieId, String movieName, String theatreName, int noOfSeats) {
		Movie myMovie=movieRepository.findById(movieId).get();
		
		Ticket ticket=new Ticket();
		ticket.setMovie(myMovie);
		ticket.setMovieName(movieName);
		ticket.setNoOfSeats(noOfSeats);
		ticket.setTheatreName(theatreName);
		Random r=new Random();
		int ticketId;
		
		while(true) {
			ticketId=r.nextInt(100000, 1000000);
			if(ticketRepository.findById(ticketId).isEmpty()) {
				break;
			}
			continue;
		}
		ticket.setTicketId(ticketId);
//		Movie myMovie=movieRepository.findById(movieId).get();
//		myMovie.getTickets().add(ticket);
//		ticket.setMovie(myMovie);
//		
		ticketRepository.save(ticket);
//		movieRepository.save(myMovie);
		
		return ticketId;
	}

	@Override
	public boolean cancelTickets(int ticketId) {
		Optional<Ticket> ticketOptional=ticketRepository.findById(ticketId);
		Ticket ticket=null;
		
		
		if(ticketOptional.isPresent()) {
			ticket=ticketOptional.get();
			ticketRepository.deleteById(ticket.getTicketId());
			Movie movie=movieRepository.findById(ticket.getMovie().getId()).get();
			movie.getTickets().remove(ticket);
			movie.setTicketsAvailable(movie.getTicketsAvailable()+ticket.getNoOfSeats());
			movieRepository.save(movie);
			return true;
		}
		return false;
	}
	
	
	
	
	
	
}
;