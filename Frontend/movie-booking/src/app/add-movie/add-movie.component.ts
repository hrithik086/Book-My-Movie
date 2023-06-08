import { Component, OnInit } from '@angular/core';
import { MovieService } from '../all-movies/movie.service';
import { NgForm } from '@angular/forms';
import { MovieDetailsRespone } from './movie-details';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent implements OnInit {

  constructor(private movieService:MovieService) { }

  ngOnInit(): void {
  }

  addMovie(movieForm:NgForm){
    let movieDetails=new MovieDetailsRespone(movieForm.value.movieName,movieForm.value.theatreName,movieForm.value.ticketsAvailable);
    this.movieService.addMovie(movieDetails).subscribe(data => {
      if(data===true){
        alert("movie added successfully");
      }
    },error => {
      alert("unable to add movie");
    });
  }

}
