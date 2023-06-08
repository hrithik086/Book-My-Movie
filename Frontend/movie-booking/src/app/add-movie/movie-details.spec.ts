import { MovieDetailsRespone } from './movie-details';

describe('MovieDetails', () => {
  it('should create an instance', () => {
    expect(new MovieDetailsRespone("thor","shakti",5)).toBeTruthy();
  });
});
