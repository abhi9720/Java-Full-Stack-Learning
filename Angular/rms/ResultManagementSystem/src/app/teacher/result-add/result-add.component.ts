import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/core/services/alert.service';
import { ResultService } from 'src/app/core/services/result.service';

@Component({
  selector: 'app-result-add',
  templateUrl: './result-add.component.html',
  styleUrls: ['./result-add.component.css']
})
export class ResultAddComponent implements OnInit {
  addForm!: FormGroup;

  constructor(private router: Router, private resultService: ResultService , private _alert:AlertService) { }

  ngOnInit() {
    this.addForm = new FormGroup({
      rollNo: new FormControl('', [Validators.required]),
      name: new FormControl('', Validators.required),
      dob: new FormControl('', Validators.required),
      score: new FormControl('', Validators.required)
    });
  }

  viewAll(){
    this.router.navigate(['results'])
  }

  addResult() {
    if (this.addForm.valid) {
      

      // Perform the add logic using the form values
      const newResult = this.addForm.value;
      console.info(newResult);

      this.resultService.addResult(newResult).subscribe(
        (response) => {
          this._alert.showSuccess("Result Added Successfully")
          console.log(response);
          this.router.navigate(['results']);
        },
        (error) => {
          this._alert.showError(error.message)
          console.dir(error);
        }
      );
    } else {
      // Handle form validation errors
      this._alert.showError("Fill All Field Correctly")
      console.log('Form is invalid');
    }
  }
}
