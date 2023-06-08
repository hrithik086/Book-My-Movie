import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TicketDetails } from './ticket-details';

@Component({
  selector: 'app-booked-ticket',
  templateUrl: './booked-ticket.component.html',
  styleUrls: ['./booked-ticket.component.css']
})
export class BookedTicketComponent implements OnInit {

  ticketId: number;
  movieName: string;
  theatreName: string;
  numberOfTickets: number

  constructor(@Inject(MAT_DIALOG_DATA) ticketDetails:TicketDetails) { 
    this.ticketId=ticketDetails.ticketId;
    this.movieName=ticketDetails.movieName;
    this.theatreName=ticketDetails.theatreName;
    this.numberOfTickets=ticketDetails.noOfTicketsBooked;
  }

  ngOnInit(): void {
  }

}
