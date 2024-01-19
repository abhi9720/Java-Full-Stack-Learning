import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms'
import { Router } from '@angular/router';
import { AlertService } from 'src/app/core/services/alert.service';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-teacher-login',
  templateUrl: './teacher-login.component.html',
  styleUrls: ['./teacher-login.component.css']
})
export class TeacherLoginComponent {

  constructor(private _router: Router, private _authservice: AuthService, private _alert:AlertService) {

  }


  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])
  })

  loginTeacher() {
    if (this.loginForm.valid) {
      console.log(this.loginForm.value)

      this._authservice.loginTeacher(this.loginForm.value).subscribe(
        (response) => {
          // Handle successful login response
          this._alert.showSuccess("Login Sucessfully")

          console.log('Login successful:', response);
          localStorage.setItem('token',response.token );
          this._router.navigate(['results']);
        },
        (error) => {
          console.dir(error)
          if(error.status===401 ){
            this._alert.showError("❌ Invalid email or password")
          }
          // Handle login error
          console.error('Login failed:', error);
        }
      );

    }
  }

}
