import { TicketDetails } from './ticket-details';

describe('TicketDetails', () => {
  it('should create an instance', () => {
    expect(new TicketDetails(12,"Doctor Strange","payal",25)).toBeTruthy();
  });
});
