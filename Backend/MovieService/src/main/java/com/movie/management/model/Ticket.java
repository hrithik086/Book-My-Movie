package com.movie.management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Ticket {
	@Id
	private int ticketId;
	
	private String movieName;
	private String theatreName;
	private int noOfSeats;
	
	@ManyToOne
	private Movie movie;

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Ticket(int ticketId, String movieName, String theatreName, int noOfSeats, Movie movie) {
		super();
		this.ticketId = ticketId;
		this.movieName = movieName;
		this.theatreName = theatreName;
		this.noOfSeats = noOfSeats;
		this.movie = movie;
	}

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
