import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginCredentials } from './login-credentials';
import { LoginResponse } from './login-response';
import { UserDetails } from '../register-user/user-details';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ResetPassword } from '../reset-password/reset-password';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  loginStatus= new BehaviorSubject<boolean>(false);
  role=new BehaviorSubject<string>("user");

  constructor(private http:HttpClient) {}

  // private login="http://localhost:8081/authentication/login";
  // private register="http://localhost:8081/authentication/registerUser";

  private login=environment.url+"/authentication/login";
  private register=environment.url+"/authentication/registerUser";
  private resetPasswordPut=environment.url+"/authentication/resetPassword";

  // private login="https://scq2warj50.execute-api.us-west-2.amazonaws.com/movie2144010/login";
  // private register="https://scq2warj50.execute-api.us-west-2.amazonaws.com/movie2144010/registeruser";
  // private resetPasswordPut="https://scq2warj50.execute-api.us-west-2.amazonaws.com/movie2144010/login";

  loginUser(loginCredentials: LoginCredentials){
    return this.http.post<LoginResponse>(this.login,loginCredentials);
  }

  registerUser(userDetails: UserDetails){
    return this.http.post<UserDetails>(this.register,userDetails);
  }

  resetPassword(resetPasswordData:ResetPassword){
    return this.http.put<boolean>(this.resetPasswordPut,resetPasswordData);
  }
}
