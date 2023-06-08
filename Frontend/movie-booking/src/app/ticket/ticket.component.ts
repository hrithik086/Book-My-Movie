import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MovieDetails } from '../all-movies/movie-details';
// import { NgFor } from '@angular/common';
import { NgForm } from '@angular/forms';
import { Ticket } from './ticket';
import { TicketService } from './ticket.service';
import { BookedTicketComponent } from '../booked-ticket/booked-ticket.component';
import { TicketDetails } from '../booked-ticket/ticket-details';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {

  id:number;
  movieName:string;
  theatreName:string;
  ticketsAvailable:number;

  constructor(@Inject(MAT_DIALOG_DATA) movieDetails:MovieDetails,private ticketService:TicketService, private matDialog:MatDialog) {
    this.id=movieDetails.id;
    this.movieName=movieDetails.movieName;
    this.theatreName=movieDetails.theatreName;
    this.ticketsAvailable=movieDetails.ticketsAvailable;
  }

  ngOnInit(): void {
  }

  bookMovies(bookMovieForm:NgForm){
    if(bookMovieForm.value.numberOfTickets > this.ticketsAvailable){
      alert("booking of seats more than available seats is not allowed");
      return;
    }
    let ticket=new Ticket(this.movieName,this.theatreName,this.id,bookMovieForm.value.numberOfTickets);
    this.ticketService.bookTicket(ticket).subscribe(data => {
      if(data>-1){
        alert("booking id is "+data);
        let ticketDetails=new TicketDetails(data,this.movieName,this.theatreName,bookMovieForm.value.numberOfTickets);
        this.matDialog.open(BookedTicketComponent,{data: ticketDetails});
      }
      else{
        alert("entered number of seats greater than available seats");
      }
    }, error => {
      alert("error occured while booking movie");
    })
  }

}
