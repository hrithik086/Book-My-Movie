import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { LoginService } from './login.service';
import { LoginCredentials } from './login-credentials';
import { MatDialog } from '@angular/material/dialog';
import { RegisterUserComponent } from '../register-user/register-user.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  

  constructor(private loginService:LoginService, private matDialog:MatDialog, private router:Router){ 
    let loginStatus=localStorage.getItem("login");
    let role=localStorage.getItem("role")
    if(loginStatus!=null && loginStatus==="true" && role!=null){
      this.loginService.loginStatus.next(true);
      this.loginService.role.next(role);
      this.router.navigateByUrl("allMovies");
    }
  }

  ngOnInit(): void {
  }

  login(loginForm: NgForm){
    // console.log(loginForm.value.username);
    let loginCredentials =new LoginCredentials(loginForm.value.username, loginForm.value.password);
    this.loginService.loginUser(loginCredentials).subscribe(data => {
      console.log(data);
      console.log(data.token);
      console.log(data.username);
      if(data.loginStatus===true){
        localStorage.setItem("token",data.token);
        localStorage.setItem("login","true");
        localStorage.setItem("role",data.role);
        this.loginService.loginStatus.next(true);
        this.loginService.role.next(data.role);
        this.router.navigateByUrl("allMovies");
      }
      else if(data.loginStatus===false){
        alert("username or password is not valid");
      }
    },error => {
      alert("username or password is not valid");
    });
  }

  openRegisterUserDialog(){
    this.matDialog.open(RegisterUserComponent);
  }

}
