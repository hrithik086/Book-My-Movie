import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookedTicketComponent } from './booked-ticket.component';
import { HttpClientModule } from '@angular/common/http';
import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { BrowserModule } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';

describe('BookedTicketComponent', () => {
  let component: BookedTicketComponent;
  let fixture: ComponentFixture<BookedTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookedTicketComponent ],
      providers: [{ provide: MAT_DIALOG_DATA, useValue: {} }],
      imports:[MatDialogModule, BrowserModule, RouterTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookedTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
