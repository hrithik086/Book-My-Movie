import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMovieComponent } from './add-movie.component';
import { MovieService } from '../all-movies/movie.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule, NgForm } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MovieDetails } from '../all-movies/movie-details';
import { MovieDetailsRespone } from './movie-details';
import { of } from 'rxjs';

describe('AddMovieComponent', () => {
  let component: AddMovieComponent;
  let fixture: ComponentFixture<AddMovieComponent>;
  let mockMovieService: MovieService;
  let httpClient: HttpClientTestingModule;
  let formValue:NgForm;
  let mockMovieDetails: MovieDetailsRespone;
  let mockHttp: HttpClient

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddMovieComponent ],
      providers: [MovieService],
      imports: [HttpClientTestingModule, FormsModule]  
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    mockMovieService=new MovieService(mockHttp);
    formValue=new NgForm([],[]);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call addMovie method', () => {
    spyOn(formValue,'value').and.returnValue({'movieName':'name','theatreName':'theatreName','ticketsAvailable':100});
    spyOn(mockMovieService,'addMovie').and.returnValue(of(true));
    component=new AddMovieComponent(mockMovieService);
    expect(component.addMovie(formValue)).toBeTruthy;
  })

});
