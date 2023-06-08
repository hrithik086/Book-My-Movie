package com.movie.management.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.management.model.Movie;
import com.movie.management.model.MovieDetails;
import com.movie.management.model.Ticket;
import com.movie.management.repo.MovieRepository;
import com.movie.management.repo.TicketRepository;
import com.movie.management.service.MovieServices;
import com.movie.management.service.TicketServices;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin()
public class TicketController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(TicketController.class);
	
	@Autowired
	MovieServices movieServices;
	
	@Autowired
	TicketServices ticketServices;
	
	@Autowired
	MovieRepository movieRepo;
	
	@PostMapping("/bookTickets")
	public ResponseEntity<?> bookMovie(@RequestBody MovieDetails movieDetails){
		boolean isTicketsAvailable=movieServices.isTicketAvailable(movieDetails.getMovieId(), movieDetails.getNoOfSeats());
		
		if(isTicketsAvailable) {
			movieServices.updateAvailableTicket(movieDetails.getMovieId(), movieDetails.getNoOfSeats());
			int ticketId=ticketServices.bookTickets(movieDetails.getMovieId(), movieDetails.getMovieName(), movieDetails.getTheatreName(), movieDetails.getNoOfSeats());
			return new ResponseEntity<Integer>(ticketId,HttpStatus.OK);
		}
		else {
			LOGGER.info("Entered seat is greater than tickets available");
			return new ResponseEntity<Integer>(-1, HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/cancelTicket/{ticketId}")
	public ResponseEntity<?> cancelTicket(@PathVariable int ticketId){
		boolean ticketStatus=ticketServices.cancelTickets(ticketId);
		
		return ticketStatus ? new ResponseEntity<Boolean>(true, HttpStatus.OK) : new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@GetMapping("/movieTest")
	public int getTickets(){
		Movie movie=movieRepo.findById(1).get();
		
		for(Ticket t: movie.getTickets()) {
			System.out.println(t.getTicketId());
		}
		
		return movie.getTickets().size();
	}
	
}