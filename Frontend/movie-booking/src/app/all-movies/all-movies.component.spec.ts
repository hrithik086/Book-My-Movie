import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllMoviesComponent } from './all-movies.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';

describe('AllMoviesComponent', () => {
  let component: AllMoviesComponent;
  let fixture: ComponentFixture<AllMoviesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllMoviesComponent ],
      imports:[HttpClientModule, FormsModule, MatDialogModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllMoviesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
