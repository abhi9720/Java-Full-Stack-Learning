import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder , Validators } from '@angular/forms';
import { NoteService } from 'src/app/services/note.service';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit {

  totalNotes =  0;
  notes:any = [  ];
  notesForm !: FormGroup

  constructor( private formBuilder : FormBuilder,
    private noteService : NoteService){

  }

  ngOnInit(): void {
    this.notesForm = this.formBuilder.group({
      content: ['', [
        Validators.required,  
        Validators.maxLength(500),  
        Validators.pattern(/^[0-9a-zA-Z\n\s@;,&*+\-]+$/) 
      ]]
    });
    this.fetchNotes()
   
  }

  fetchNotes(){
    this.noteService.getNotes().subscribe(
      (data:any)=> {
        this.totalNotes =  data.totalCount
        this.notes  =  data.recentnotes;
        console.log(this.notes);
        console.log(data);
        
        
      },
      (error:any)=>{
        console.log("Something went wrong", error);
        
      }
    )
  }

  addNote(): void{
    if(this.notesForm.invalid){
      return
    }
    const content = this.notesForm.get("content")?.value;
    console.log(content);
    
    this.noteService.addNote(content).subscribe(
      (response)=>{
        this.totalNotes+=1;
        this.notes.unshift(response);
        if(this.notes.length > 10){
          this.notes.splice(10)
        }
        this.notesForm.reset()
      },
      (error) => {
        console.log("Error adding note : ", error);
        
      }
    )
  }


  deleteNote(id:number){
    this.noteService.deleteNote(id).subscribe(
      (res)=>{
        console.log(res);
        
        this.fetchNotes()
      }
      ,
      (error)=>{

      }
    )
  }


}
