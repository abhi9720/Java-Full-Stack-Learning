import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{

  registerForm !: FormGroup;

  constructor(private fb : FormBuilder, private authService : AuthService, private router : Router){

  }

  ngOnInit(){

    this.registerForm  =  this.fb.group({
      name : ['', Validators.required],
      email : ['', [Validators.required, Validators.email]],
      password : ['', Validators.required]
    })

  }


  onSubmit(){

    console.log(this.registerForm.value);

    const {name , email, password} =  this.registerForm.value;

    this.authService.register(name , email, password).subscribe(
      (res)=>{
        console.log(res);
        this.router.navigate(["/login"])

      },
      (err)=>{
        console.log(err);
        
      }
    )
    

  }

}
