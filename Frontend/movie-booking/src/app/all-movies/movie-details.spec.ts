import { MovieDetails } from './movie-details';

describe('MovieDetails', () => {
  it('should create an instance', () => {
    expect(new MovieDetails(1,"iron man","shiva",12)).toBeTruthy();
  });
});
