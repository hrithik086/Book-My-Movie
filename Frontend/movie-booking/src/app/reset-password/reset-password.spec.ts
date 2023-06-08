import { ResetPassword } from './reset-password';

describe('ResetPassword', () => {
  it('should create an instance', () => {
    expect(new ResetPassword(10,"username","password")).toBeTruthy();
  });
});
