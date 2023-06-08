export class LoginResponse {

    constructor(
        public username:string,
        public token:string,
        public role:string,
        public message:string,
        public loginStatus: boolean
    ){}

}
