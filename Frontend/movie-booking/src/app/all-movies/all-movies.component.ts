import { Component, OnInit } from '@angular/core';
import { MovieService } from './movie.service';
import { MovieDetails } from './movie-details';
import { LoginService } from '../login/login.service';
import { MatDialog } from '@angular/material/dialog';
import { UpdateMovieComponent } from '../update-movie/update-movie.component';
import { TicketComponent } from '../ticket/ticket.component';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-all-movies',
  templateUrl: './all-movies.component.html',
  styleUrls: ['./all-movies.component.css']
})
export class AllMoviesComponent implements OnInit {

  constructor(private movieService:MovieService, private loginService:LoginService, private matDialog:MatDialog) { 
    this.dataSource=[];
    this.mainDataSource=[];
    this.movieService.getMovies().subscribe(data => {
      console.log(data);
      this.dataSource=data;
      this.mainDataSource=data;
    })
    loginService.role.subscribe(data => {
      if(data==="admin"){
        this.isAdmin=true;
      }
    })
  }

  ngOnInit(): void {
  }

  isAdmin:boolean=false;

  displayedColumns: string[] = ['id', 'movieName', 'theatreName', 'ticketsAvailable', 'action'];
  dataSource: MovieDetails[];
  mainDataSource: MovieDetails[];

  openUpdateMovie(id:number, movieName:string, theatreName:string, ticketsAvailable:number){
    let myUpdateDetails=new MovieDetails(id, movieName, theatreName, ticketsAvailable);
    this.matDialog.open(UpdateMovieComponent,{data: myUpdateDetails});
  }

  deleteMovie(id:number){
    this.movieService.deleteMovie(id).subscribe(data => {
      if(data===true){
        alert("movie deleted");
      }
      else{
        alert("not deleted");
      }
    },error => {
      alert("error occured while deleting");
    });
  }

  openBookMovie(id:number, movieName:string, theatreName:string, ticketsAvailable:number){
    let movieDetails=new MovieDetails(id, movieName, theatreName, ticketsAvailable);
    this.matDialog.open(TicketComponent,{data: movieDetails});
  }

  searchMovie(searchForm:NgForm){
    let tempDataSource:MovieDetails[];
    tempDataSource=[];
    if(searchForm.value.search===null || searchForm.value.search===""){
      this.dataSource=this.mainDataSource;
      return;
    }
    for(let element of this.mainDataSource){
      if(element.movieName.startsWith(searchForm.value.search)){
        tempDataSource.push(element)
      }
    }
    this.dataSource=tempDataSource;
  }

  clearSearch(){
    this.dataSource=this.mainDataSource;
  }

}
