import { LoginResponse } from './login-response';

describe('LoginResponse', () => {
  it('should create an instance', () => {
    expect(new LoginResponse("username","token","role","message",true)).toBeTruthy();
  });
});
