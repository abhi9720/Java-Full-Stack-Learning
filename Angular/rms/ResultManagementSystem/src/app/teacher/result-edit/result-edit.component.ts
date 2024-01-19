import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ResultService } from 'src/app/core/services/result.service';
import { AlertService } from 'src/app/core/services/alert.service';
@Component({
  selector: 'app-result-edit',
  templateUrl: './result-edit.component.html',
  styleUrls: ['./result-edit.component.css']
})
export class ResultEditComponent {

  editForm!: FormGroup;

  rollNo!: number;
  constructor(private route: ActivatedRoute, private _router: Router, private _resultservice: ResultService, private _alert: AlertService) { }

  ngOnInit() {
    let rollno = this.route.snapshot.paramMap.get('rollNo');
    if (rollno != null) {
      this.rollNo = parseInt(rollno);
    }
    else {
      this._router.navigate(['/results']);
    }

    this.editForm = new FormGroup({
      rollNo: new FormControl('', [Validators.required]),
      name: new FormControl('', Validators.required),
      dob: new FormControl('', Validators.required),
      score: new FormControl('', Validators.required)
    });

    // Fetch the result data using the rollNo value and set the form values
    this.fetchResultData();
  }

  fetchResultData() {
    // Fetch the result data using the rollNo value and perform necessary operations
    // ... this.editForm =  from api

    // Simulate fetching result data based on rollNo
    this._resultservice.getResultById(this.rollNo).subscribe(
      response => this.editForm.patchValue(response)
    );
    // Set the form values using patchValue
  }




  goBack() {
    this._router.navigate(['/results']);
  }


  updateResult() {
    if (this.editForm.valid) {
      // Perform the update logic using the form values
      const updatedResult = this.editForm.value;
      console.log(updatedResult);
      this._resultservice.updateResult(this.rollNo, updatedResult).subscribe(

        (response) => {
          console.log(response);
          this._alert.showSuccess(`RollNo. ${response.rollNo} Result Updated Successfuly`)
          this.goBack()

        },
        (error) => {
          this._alert.showError(error.message);
          console.dir(error);
        }

      )
    } else {
      this._alert.showError(`Form Invalid! Fill all Field Correctly`)
      // Handle form validation errors
      console.log('Form is invalid');
    }
  }
}
