import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginService } from '../login/login.service';
import { UserDetails } from './user-details';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  constructor(private loginService:LoginService) { }

  ngOnInit(): void {
  }

  registerUser(registerForm:NgForm){
    let userDetails=new UserDetails(registerForm.value.username,registerForm.value.password,registerForm.value.role,false);
    this.loginService.registerUser(userDetails).subscribe(data => {
      if(data.registerStatus==true){
        alert("user added");
      }
      else{
        alert("unable to add user");
      }
    })
  }

}
