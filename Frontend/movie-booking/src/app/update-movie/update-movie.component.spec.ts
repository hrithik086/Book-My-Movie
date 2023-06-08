import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateMovieComponent } from './update-movie.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';

describe('UpdateMovieComponent', () => {
  let component: UpdateMovieComponent;
  let fixture: ComponentFixture<UpdateMovieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateMovieComponent ],
      providers: [{provide: MAT_DIALOG_DATA, useValue: {}}],
      imports: [HttpClientModule, FormsModule, MatDialogModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
