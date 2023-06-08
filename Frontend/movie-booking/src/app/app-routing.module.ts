import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AllMoviesComponent } from './all-movies/all-movies.component';
import { AddMovieComponent } from './add-movie/add-movie.component';
import { AuthGuard } from './auth/auth.guard';
import { ResetPasswordComponent } from './reset-password/reset-password.component';

const routes: Routes = [
  {path: "", redirectTo: "login", pathMatch: "full"},
  {path: "login", component:LoginComponent},
  {path: "allMovies", canActivate:[AuthGuard], component: AllMoviesComponent},
  {path: "resetPassword", component: ResetPasswordComponent}
  // {path: "addMovie", canActivate:[AuthGuard],component: AddMovieComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
