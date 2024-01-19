import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import jwt_decode from "jwt-decode";
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = "http://localhost:8180/api"

  constructor(private _http: HttpClient) { }

  login(email: string, password: string):Observable<any> {
    const loginBody =  {email, password}
    return this._http.post<any>(`${this.apiUrl}/users/login`, loginBody);
  }

  register(name: string, email: string, password: string) {
    const registerBody =  {name , email, password};
    return this._http.post<any>(`${this.apiUrl}/users/register`, registerBody);

  }


  isAuthenticated() {
    const token = this.getAuthToken();
    if (token) {
      try {
        const decodedToken: any = jwt_decode(token);
        if (decodedToken && decodedToken.exp && decodedToken.exp * 1000 > Date.now()) {
          return true;
        }
        console.log(decodedToken, decodedToken.exp * 100 > Date.now());
        

      }
      catch (error) {
        console.log(error);

      }
    }

    return false;
  }

  getAuthToken() {
    return localStorage.getItem("authtoken");
  }


  saveToken(token:string){
    localStorage.setItem("authtoken", token)
  }

  logOut() {
    localStorage.removeItem("authtoken")
  }
}
