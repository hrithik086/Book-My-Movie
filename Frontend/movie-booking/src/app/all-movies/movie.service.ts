import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MovieDetails } from './movie-details';
import { MovieDetailsRespone } from '../add-movie/movie-details';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  constructor(private http:HttpClient) { }

  // getAllMovies="http://localhost:8081/api/v1/allMovies";
  // addNewMovie="http://localhost:8081/api/v1/addMovies";
  // updateMoviePut="http://localhost:8081/api/v1/updateMovie";
  // deleteMovieDelete="http://localhost:8081/api/v1/deleteMovieById/"

  getAllMovies=environment.url+"/api/v1/allMovies";
  addNewMovie=environment.url+"/api/v1/addMovies";
  updateMoviePut=environment.url+"/api/v1/updateMovie";
  deleteMovieDelete=environment.url+"/api/v1/deleteMovieById/"

  // getAllMovies="https://scq2warj50.execute-api.us-west-2.amazonaws.com/movie2144010/movie";
  // addNewMovie="https://scq2warj50.execute-api.us-west-2.amazonaws.com/movie2144010/movie";
  // updateMoviePut="https://scq2warj50.execute-api.us-west-2.amazonaws.com/movie2144010/movie";
  // deleteMovieDelete=" https://scq2warj50.execute-api.us-west-2.amazonaws.com/movie2144010/movie/";

  getMovies(){
    let token="Bearer "+localStorage.getItem("token");
    let head=new HttpHeaders().set("Authorization",token);
    return this.http.get<MovieDetails[]>(this.getAllMovies,{headers: head});
  }

  addMovie(movieDetails:MovieDetailsRespone){
    let token="Bearer "+localStorage.getItem("token");
    let head=new HttpHeaders().set("Authorization",token);
    return this.http.post<boolean>(this.addNewMovie,movieDetails,{headers: head});
  }

  updateMovie(movieDetails: MovieDetails){
    let token="Bearer "+localStorage.getItem("token");
    let head=new HttpHeaders().set("Authorization",token);
    return this.http.put<boolean>(this.updateMoviePut,movieDetails,{headers: head});
  }

  deleteMovie(id:number){
    let token="Bearer "+localStorage.getItem("token");
    let head=new HttpHeaders().set("Authorization",token);
    this.deleteMovieDelete=this.deleteMovieDelete+id;
    return this.http.delete<boolean>(this.deleteMovieDelete,{headers: head});
  }
}
