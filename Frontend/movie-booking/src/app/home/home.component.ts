import { Component, OnInit } from '@angular/core';
import { NgbCarouselModule } from '@ng-bootstrap/ng-bootstrap';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  //images with width=900px and height=500 px are suppoerts best
  images = [22, 58, 49].map((n) => `https://picsum.photos/id/${n}/900/500?blur&grayscale`);

  labels = [
    "Movie 1",
    "Movie 2",
    "Movie 3"
  ];

  labelDetails= [
    "Movie 1 Details",
    "Movie 2 Details",
    "Movie 3 Details"
  ]


}
