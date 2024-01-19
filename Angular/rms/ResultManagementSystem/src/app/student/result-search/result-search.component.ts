import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Result } from 'src/app/core/models/result.model';
import { AlertService } from 'src/app/core/services/alert.service';
import { ResultService } from 'src/app/core/services/result.service';

@Component({
  selector: 'app-result-search',
  templateUrl: './result-search.component.html',
  styleUrls: ['./result-search.component.css']
})
export class ResultSearchComponent  {

  constructor(private _resultservice:ResultService, private _alert : AlertService){

  }

  resultFetched = false ; // Flag to control whether to show the form or data
  reportCard:any=null ;

  searchForm =  new FormGroup({
    dob: new FormControl('', [Validators.required]),
    rollNo : new FormControl('', [Validators.required ])
  })

  showForm() {
    this.reportCard =null;
    this.searchForm.reset();
    this.resultFetched = false; // Set the flag to show the form
  }

  searchResults() {
    this.reportCard = null;
    if (this.searchForm.valid) {
      this._resultservice.searchResult(this.searchForm.value).subscribe(
        (response)=>{
          console.log(response);
          this.reportCard =  response;
          this.resultFetched = true;
        }
        ,
        (error)=>{
          this._alert.showError(error.message)
          console.error(error);
        }
      )
       // Set the flag to show the fetched data

    
    }
  }

  clearForm() {
    this.searchForm.reset();
  }
}
