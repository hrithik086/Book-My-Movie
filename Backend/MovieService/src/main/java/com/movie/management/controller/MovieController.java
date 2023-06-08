package com.movie.management.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.management.Exception.DuplicateMovieException;
import com.movie.management.Exception.NoMovieFound;
import com.movie.management.model.Movie;
import com.movie.management.service.JwtService;
import com.movie.management.service.MovieServices;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin()
public class MovieController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	private MovieServices movieServices;
	
	@Autowired
	private JwtService jwtService;
	
	@GetMapping("/test")
	public String test() {
		return "Movie Service application working";
	}
	
	@GetMapping("/allMovies")
	public ResponseEntity<?> getAllMovies(@RequestHeader("Authorization") String token){
		boolean isTokenValid= jwtService.validateToken(token);
		if(isTokenValid) {
			return new ResponseEntity<List<Movie>>(movieServices.getAllMovies(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Movie>>(new ArrayList<>(),HttpStatus.CONFLICT);
		}
//		return new ResponseEntity<List<Movie>>(movieServices.getAllMovies(), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteMovieById/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable int id, @RequestHeader("Authorization") String authorization){
		
		boolean isTokenValid= jwtService.validateToken(authorization);
		
		if(!isTokenValid || !jwtService.getRole(authorization.substring(7)).equalsIgnoreCase("admin")) {
			return new ResponseEntity<String>("only admins are allowed for this", HttpStatus.OK);
		}
		
		try {
			boolean deletionStatus=movieServices.deleteMovie(id);
			if(deletionStatus)
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (NoMovieFound e) {
			return new ResponseEntity<Boolean>(false,HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.CONFLICT);
	}
	
	@PutMapping("/updateMovie")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie, @RequestHeader("Authorization") String authorization){
		
		boolean isTokenValid= jwtService.validateToken(authorization);
		
		if(!isTokenValid || !jwtService.getRole(authorization.substring(7)).equalsIgnoreCase("admin")) {
			LOGGER.warn("user is not an admin");
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		
		try {
			boolean isMovieUpdated=movieServices.updateMovie(movie);
			if(isMovieUpdated) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
		} catch (NoMovieFound e) {
		}
		
		return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
	}
	
	@PostMapping("/addMovies")
	public ResponseEntity<?> addMovie(@RequestBody Movie movie, @RequestHeader("Authorization") String authorization){
		
		boolean isTokenValid= jwtService.validateToken(authorization);
		
		if(!isTokenValid || !jwtService.getRole(authorization.substring(7)).equalsIgnoreCase("admin")) {
			return new ResponseEntity<String>("only admins are allowed for this", HttpStatus.OK);
		}
		
		try {
			boolean isMovieAdded=movieServices.addMovie(movie);
			if(isMovieAdded) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
		} catch (DuplicateMovieException e) {
			
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
	}
	
}