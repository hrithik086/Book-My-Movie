package com.movie.management.service;

public interface TicketServices {
	int bookTickets(int movieId, String movieName, String theatreName, int noOfSeats);
	boolean cancelTickets(int ticketId);
}
