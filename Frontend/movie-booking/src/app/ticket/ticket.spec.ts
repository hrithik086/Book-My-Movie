import { Ticket } from './ticket';

describe('Ticket', () => {
  it('should create an instance', () => {
    expect(new Ticket("Captain America","Rukmini",102,100)).toBeTruthy();
  });
});
