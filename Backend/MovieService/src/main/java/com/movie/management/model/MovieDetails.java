package com.movie.management.model;

public class MovieDetails {
	private String movieName;
	private String theatreName;
	private int movieId;
	private int noOfSeats;
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
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public MovieDetails(String movieName, String theatreName, int movieId, int noOfSeats) {
		super();
		this.movieName = movieName;
		this.theatreName = theatreName;
		this.movieId = movieId;
		this.noOfSeats = noOfSeats;
	}
	public MovieDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
}
