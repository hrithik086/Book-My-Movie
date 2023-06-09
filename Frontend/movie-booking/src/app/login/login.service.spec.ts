import { TestBed } from '@angular/core/testing';

import { LoginService } from './login.service';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { LoginCredentials } from './login-credentials';
import { LoginResponse } from './login-response';
import { of } from 'rxjs';
import { UserDetails } from '../register-user/user-details';
import { ResetPassword } from '../reset-password/reset-password';

describe('LoginService', () => {
  let service: LoginService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(LoginService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call loginUser',() => {
    let loginCredentials=new LoginCredentials("username","password");
    let loginResponse=new LoginResponse("username","Bearer sdfasdfa.dafdadf.dfgfgd","User","user logged in",true);
    spyOn(service,"loginUser").and.returnValue(of(loginResponse));
    service.loginUser(loginCredentials).subscribe(data => {
      expect(data).toEqual(loginResponse);
    })
  });

  it('should registerUser',() => {
    let userDetails=new UserDetails("username","password","role",true);
    spyOn(service,"registerUser").and.returnValue(of(userDetails));
    service.registerUser(userDetails).subscribe(data => {
      expect(data).toEqual(userDetails);
    })
  });

  it('should resetPassword',() => {
    let resetPasswordData=new ResetPassword(3,"user","pas");
    spyOn(service,"resetPassword").and.returnValue(of(true));
    service.resetPassword(resetPasswordData).subscribe(data => {
      expect(data).toBe(true);
    })
  })
});
