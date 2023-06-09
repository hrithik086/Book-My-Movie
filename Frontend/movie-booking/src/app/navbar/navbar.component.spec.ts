import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarComponent } from './navbar.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { LoginService } from '../login/login.service';
import { Router } from '@angular/router';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

describe('NavbarComponent', () => {
  let component: NavbarComponent;
  let fixture: ComponentFixture<NavbarComponent>;

  let loginService: LoginService;
  let router: Router;
  let matDialog: MatDialog;
  let navComponent:NavbarComponent;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavbarComponent ],
      providers: [LoginService],
      imports: [HttpClientTestingModule, MatDialogModule, RouterTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    loginService=TestBed.inject(LoginService);
    router= jasmine.createSpyObj("Router",["navigateByUrl"]);
    matDialog=jasmine.createSpyObj("MatDialog",["open"]);
    navComponent=new NavbarComponent(router,matDialog,loginService);

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call get all movies method', () => {
    component=navComponent;
    component.getAllMovies();
    expect(router.navigateByUrl).toHaveBeenCalled();
  })

  it('should call addMovieDialog method', ()=> {
    component=navComponent;
    component.addMovieDialog();
    expect(matDialog.open).toHaveBeenCalled();
  })

  it('should call logout method', ()=> {
    component=navComponent;
    component.logOut();
    expect(router.navigateByUrl).toHaveBeenCalled();
  })

});
