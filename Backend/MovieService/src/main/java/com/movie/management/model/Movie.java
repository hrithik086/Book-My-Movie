package com.movie.management.model;

import java.time.LocalTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String movieName;
	private String theatreName;
	private int ticketsAvailable;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "movie")
	@JsonIgnore
	private Set<Ticket> tickets;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getTicketsAvailable() {
		return ticketsAvailable;
	}
	public void setTicketsAvailable(int ticketsAvailable) {
		this.ticketsAvailable = ticketsAvailable;
	}
	public Set<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}
	public Movie(int id, String movieName, String theatreName, int ticketsAvailable, Set<Ticket> tickets) {
		super();
		this.id = id;
		this.movieName = movieName;
		this.theatreName = theatreName;
		this.ticketsAvailable = ticketsAvailable;
		this.tickets = tickets;
	}
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
