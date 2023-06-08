import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MovieDetails } from '../all-movies/movie-details';
import { NgForm } from '@angular/forms';
import { MovieService } from '../all-movies/movie.service';

@Component({
  selector: 'app-update-movie',
  templateUrl: './update-movie.component.html',
  styleUrls: ['./update-movie.component.css']
})
export class UpdateMovieComponent implements OnInit {

  id:number;
  movieName:string;
  theatreName:string;
  ticketsAvailable:number;
  isDisable=true;
  constructor(@Inject(MAT_DIALOG_DATA) data:MovieDetails,private movieService:MovieService) {
    this.id=data.id;
    this.movieName=data.movieName;
    this.theatreName=data.theatreName;
    this.ticketsAvailable=data.ticketsAvailable;
   }

  ngOnInit(): void {
  }

  updateMovie(updateMovieForm:NgForm){
    let movieDetails=new MovieDetails(this.id, updateMovieForm.value.movieName, updateMovieForm.value.theatreName, updateMovieForm.value.ticketsAvailable);
    this.movieService.updateMovie(movieDetails).subscribe(data => {
      if(data===true){
        alert("movie update successfully");
      }
      else{
        alert("unable to update movie");
      }
    }, error => {
      alert("some error occured while updating movie");
    })
  }

}
