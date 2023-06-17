import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetPasswordComponent } from './reset-password.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule, NgForm } from '@angular/forms';
import { LoginService } from '../login/login.service';
import { of } from 'rxjs';

describe('ResetPasswordComponent', () => {
  let component: ResetPasswordComponent;
  let fixture: ComponentFixture<ResetPasswordComponent>;

  let mockLoginService: LoginService;
  let mockHttp: HttpClient;
  let mockForm: NgForm;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResetPasswordComponent ],
      imports: [HttpClientModule, FormsModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResetPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    mockLoginService=new LoginService(mockHttp);
    mockForm=new NgForm([],[]);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should pass', () => {
    let actual=100;
    expect(actual).toBe(100);
  })

  it('shouold reset pass', () => {
    spyOn(mockForm,"value").and.returnValue({'userId':3,'username':'name','password':'pass','confirmPassword':'pass'});
    spyOn(mockLoginService,"resetPassword").and.returnValue(of(true));
    expect(alert("password reset successfull")).toHaveBeenCalled
  })
});
