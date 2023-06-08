import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login/login.service';
import { NgForm } from '@angular/forms';
import { ResetPassword } from './reset-password';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  constructor(private loginService:LoginService) { }

  ngOnInit(): void {
  }

  resetPassword(resetForm:NgForm){
    if(resetForm.value.password===resetForm.value.confirmPassword){
      let resetPasswordData=new ResetPassword(resetForm.value.userId,resetForm.value.username,resetForm.value.password);
      this.loginService.resetPassword(resetPasswordData).subscribe(data => {
        if(data===true){
          alert("password reset successfull");
        }
        else{
          alert("unable to reset password");
        }
      }, error => {
        alert("error occured while resetting password");
      })
    }
    else{
      alert("password and confirm password are not same enter carefully");
    }

    
  }

}
