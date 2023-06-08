import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AddMovieComponent } from '../add-movie/add-movie.component';
import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn:boolean=false;
  isAdmin:boolean=false;

  constructor(private router:Router, private matDialog: MatDialog, private loginService:LoginService) {
    this.loginService.loginStatus.subscribe(data => {
      this.isLoggedIn=data;
    });
    this.loginService.role.subscribe(data => {
      if(data==='admin'){
        this.isAdmin=true;
      }
      else{
        this.isAdmin=false;
      }
    })
  }

  ngOnInit(): void {
  }

  getAllMovies(){
    this.router.navigateByUrl("allMovies")
  }

  addMovieDialog(){
    this.matDialog.open(AddMovieComponent);
  }

  logOut(){
    localStorage.setItem("token","");
    localStorage.setItem("login","false");
    this.loginService.loginStatus.next(false);
    this.router.navigateByUrl("login");
  }

  openResetPasswordComponent(){
    this.router.navigateByUrl("resetPassword")
  }



}
