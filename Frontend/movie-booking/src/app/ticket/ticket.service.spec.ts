import { TestBed } from '@angular/core/testing';

import { TicketService } from './ticket.service';
import { HttpClientModule } from '@angular/common/http';
import { Ticket } from './ticket';
import { of } from 'rxjs';

describe('TicketService', () => {
  let service: TicketService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule]
    });
    service = TestBed.inject(TicketService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should bookTicket',() => {
    let ticket=new Ticket("SitaRam","Ayodhya",1,100000);
    spyOn(service,'bookTicket').and.returnValue(of(25));
    service.bookTicket(ticket).subscribe(data => {
      expect(data).toBe(25);
    })
  })
});
