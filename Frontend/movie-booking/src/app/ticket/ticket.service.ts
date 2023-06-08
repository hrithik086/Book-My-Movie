import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ticket } from './ticket';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  constructor(private http:HttpClient) { }

  // bookTicketPost="http://localhost:8081/api/v1/bookTickets";
  bookTicketPost=environment.url+"/api/v1/bookTickets";

  // bookTicketPost="https://scq2warj50.execute-api.us-west-2.amazonaws.com/movie2144010/ticket";

  bookTicket(ticket:Ticket){
    let token="Bearer "+localStorage.getItem("token");
    let head=new HttpHeaders().set("Authorization",token);
    return this.http.post<number>(this.bookTicketPost,ticket,{headers: head});
  }
}
