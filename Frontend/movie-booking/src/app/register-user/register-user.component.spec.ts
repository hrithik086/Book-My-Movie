import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterUserComponent } from './register-user.component';
import { LoginService } from '../login/login.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, NgForm } from '@angular/forms';
import { of } from 'rxjs';
import { UserDetails } from './user-details';

describe('RegisterUserComponent', () => {
  let component: RegisterUserComponent;
  let fixture: ComponentFixture<RegisterUserComponent>;

  let mockLoginService: LoginService;
  let mockForm: NgForm;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterUserComponent ],
      imports:[HttpClientModule, FormsModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    mockLoginService=TestBed.inject(LoginService);
    mockForm=new NgForm([],[]);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should register user', () => {
    spyOn(mockForm,"value").and.returnValue({'username':'username','password':'password','role':'admin'});
    // spyOn(mockLoginService,"registerUser");
    spyOn(mockLoginService,"registerUser").and.returnValue(of(new UserDetails("username","password","admin",true)));
    // expect(component.registerUser(mockForm)).toBeTruthy;
    component.registerUser(mockForm);
    expect(mockLoginService.registerUser).toHaveBeenCalled
  })
});
