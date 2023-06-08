package com.movie.management.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.management.model.Movie;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
