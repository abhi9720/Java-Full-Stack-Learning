import { Injectable } from '@angular/core';
import { Result } from '../models/result.model';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ResultService {

  private baseUrl = 'http://localhost:3000';

  constructor(private _http: HttpClient) { }

  getAllResults(): Observable<Result[]> {
    return this._http.get<Result[]>(`${this.baseUrl}/results`);
  }


  addResult(result: Result): Observable<Result> {
    return this._http.post<Result>(`${this.baseUrl}/results`, result).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 400 && error.error.error === 'RollNo already exists') {
          return throwError(() => new Error('RollNo already exists'));
        } else {
          return throwError(() => new Error('Failed to add result'));
        }
      })
    );
  }

  deleteResult(rollNo: number) {
    return this._http.delete(`${this.baseUrl}/results/${rollNo}`)
  }

  getResultById(rollNo: number) {
    return this._http.get<Result>(`${this.baseUrl}/results/${rollNo}`)
  }

  updateResult(rollNo: number, data: Result) {
    return this._http.put<Result>(`${this.baseUrl}/results/${rollNo}`, data).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 404) {
          return throwError(() => new Error('Result not found'));
        } else if (error.status === 400 && error.error.error === 'Duplicate roll number') {
          return throwError(() => new Error('Duplicate roll number'));
        } else {
          return throwError(() => new Error('Failed to update result'));
        }
      })
    )
  }

  searchResult(queryobj: any) {
    const { rollNo, dob } = queryobj;
    let params = new HttpParams();
    params = params.set('rollNo', rollNo);
    params = params.set('dob', dob);
    return this._http.get<Result>(`${this.baseUrl}/students/search`, { params }).pipe(
      catchError((error: HttpErrorResponse)=>{
        if(error.status===404){
          return throwError(()=> new Error('Result Not Found!'));
        }
        else{
          return throwError(() => new Error('something Went Wrong'));
        }
      })
    )
  }

}
