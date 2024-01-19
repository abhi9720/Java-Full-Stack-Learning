import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable} from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  private apiUrl =  "http://localhost:8180/api"
  constructor(private _http : HttpClient) { }

  getNotes() : Observable<any>{
    return this._http.get<any>(`${this.apiUrl}/notes/recent`) 
  }

  addNote(content: string): Observable<any> {
    const noteDate =  {content};
    console.log(noteDate);
    
    return this._http.post(`${this.apiUrl}/notes`, noteDate)
  }

  deleteNote(id:number){
    return this._http.delete(`${this.apiUrl}/notes/${id}`);
  }
}
