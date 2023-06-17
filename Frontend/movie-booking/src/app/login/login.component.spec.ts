import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { LoginService } from './login.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterModule, UrlSerializer } from '@angular/router';
import { of } from 'rxjs';
import { LoginResponse } from './login-response';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  let mockLoginService: LoginService;
  let mockMatDialog: MatDialog;
  let mockRouter: Router;
  let mockHttp: HttpClient;
  let mockForm: NgForm;
  let mockLoginResponse: LoginResponse;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      providers: [LoginService, MatDialog],
      imports: [HttpClientModule, MatDialogModule, FormsModule, RouterModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    mockLoginService=TestBed.inject(LoginService);
    mockRouter=TestBed.inject(Router);
    mockForm=new NgForm([],[]);
  });

  it('should create', () => {
    spyOn(component,'login');
    expect(component).toBeTruthy();
  });

  it('should able to login',() => {
    // component=new LoginComponent(mockLoginService,mockMatDialog,mockRouter);
    spyOn(mockForm,"value").and.returnValue({'username':'username','password':'password'});
    spyOn(mockLoginService,"loginUser").and.returnValue(of(new LoginResponse("username","token","admin","message",true)));
    spyOn(mockRouter,"navigateByUrl");
    expect(component.login(mockForm)).toBeTruthy
    // expect(mockRouter.navigateByUrl).toHaveBeenCalled;
  })
});
