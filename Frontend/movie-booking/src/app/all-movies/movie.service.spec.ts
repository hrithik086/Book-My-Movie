import { TestBed } from '@angular/core/testing';

import { MovieService } from './movie.service';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MovieDetails } from './movie-details';
import { of } from 'rxjs';

describe('MovieService', () => {
  let service: MovieService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(MovieService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call get movie', ()=> {
    let response: MovieDetails[]|any;
    spyOn(service,'getMovies').and.returnValue(of(response));
    service.getMovies().subscribe(data => {
      expect(data).toEqual(response);
    })
  });

  it('should call addMovie method',()=> {
    let movieDetails=new MovieDetails(1,"new movie","Shiva Shakti",1000);
    spyOn(service,'addMovie').and.returnValue(of(true));
    service.addMovie(movieDetails).subscribe(data=> {
      expect(data).toBe(true);
    })
  });

  it('should call updateMovie method', ()=> {
    let movieDetails=new MovieDetails(2,"movie2","Rukmini",5000);
    spyOn(service,'updateMovie').and.returnValue(of(true));
    service.updateMovie(movieDetails).subscribe(data=> {
      expect(data).toBe(true);
    })
  });

  it('should call deleteMovie',() => {
    spyOn(service,'deleteMovie').and.returnValue(of(true));
    service.deleteMovie(1).subscribe(data => {
      expect(data).toBe(true);
    })
  })
});
