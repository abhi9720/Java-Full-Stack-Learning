import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private baseUrl = 'http://localhost:3000/teachers'
  constructor(private _http: HttpClient,private _router: Router) { }

  loginStudent(data: any) {
    localStorage.setItem('userType','Student');

    

  }

  loginTeacher(loginData:any): Observable<any> {
    const url = `${this.baseUrl}/login`; // Update with your login API endpoint
    return this._http.post<any>(url, loginData);
  }


  logOutUser() {
    localStorage.removeItem('token');
  }

  isStudentLoggedIn(): boolean {
    const userType = localStorage.getItem('userType');
    console.log(userType !== null && userType === 'Student')
    return userType !== null && userType === 'Student';
  }
  


  isTeacherLoggedIn(): boolean {
    const token = localStorage.getItem('token');
    if (token) {
      try {
        const decodedToken: any = jwt_decode(token);
        // Check if the decoded token has the expected properties
        if (decodedToken && decodedToken.id && decodedToken.role === 'Teacher') {
          return true;
        }
      } catch (error) {
        console.error('Invalid token:', error);
      }
    }
    this.logOutUser()
    // Token is not valid or user is not a teacher
    return false;
  }

  getLoginUserType(){
    return localStorage.getItem('userType');
  }


}
