import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllMoviesComponent } from './all-movies.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { LoginService } from '../login/login.service';
import { MovieService } from './movie.service';
import { never, of } from 'rxjs';
import { MovieDetails } from './movie-details';

describe('AllMoviesComponent', () => {
  let component: AllMoviesComponent;
  let fixture: ComponentFixture<AllMoviesComponent>;

  let mockLoginService: LoginService;
  let mockMovieService: MovieService;
  let mockMatDialog: MatDialog;
  let mockHttp: HttpClient;
  let mockDataSource: MovieDetails[];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllMoviesComponent ],
      imports:[HttpClientModule, FormsModule, MatDialogModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllMoviesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    mockMovieService=new MovieService(mockHttp);
    // mockLoginService= new LoginService(mockHttp);
    // mockLoginService=jasmine.createSpyObj("LoginService",["role"]);
    // spyOn(mockMovieService,"getMovies").and.returnValue(of(mockDataSource));
    // mockLoginService
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
