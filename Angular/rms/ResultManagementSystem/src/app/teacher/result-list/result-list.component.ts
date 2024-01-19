import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Result } from 'src/app/core/models/result.model';
import { AlertService } from 'src/app/core/services/alert.service';
import { ResultService } from 'src/app/core/services/result.service';

@Component({
  selector: 'app-result-list',
  templateUrl: './result-list.component.html',
  styleUrls: ['./result-list.component.css']
})
export class ResultListComponent  implements OnInit{
  constructor(private _router:Router,private _resultservice:ResultService,private _alertService: AlertService){

  }
  results:Result[] = []
  deleteSuccessMessage: string | null = null;

  

  ngOnInit(): void {
     this._resultservice.getAllResults().subscribe(response =>{
      this.results =  response
     })
     console.log()
    
  }

  editResult(data:Result){
    this._router.navigate(['/result/edit', data.rollNo]);
  }


  
  deleteResult(rollNo: number) {
    this._resultservice.deleteResult(rollNo)
    .subscribe({
      next: () => {
        const index = this.results.findIndex(result => result.rollNo === rollNo);
        if (index !== -1) {
          // Remove the record from the results array
          this.results.splice(index, 1);
        }
        this._alertService.showSuccess('Result deleted successfully!' );
        // this.triggerAutoClose();
      },
      error: error => {
         this._alertService.showError('Error deleting result' );

        console.log('Error deleting result:', error);
        // Handle the error here, e.g., display an error message
      }
    });
    // this.triggerAutoClose();

  }

 


 
}
