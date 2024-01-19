import { Component , OnInit } from '@angular/core';
import { FormGroup, FormBuilder , Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm !:  FormGroup;
 

  constructor( private fb : FormBuilder , private authService : AuthService, private router : Router){
  }

  ngOnInit(){
    this.loginForm =  this.fb.group({
      email : ['',[Validators.required, Validators.email]],
      password : ['', Validators.required]
    })

   
  }


  onSubmit(){

    if(this.loginForm.valid){
      const email = this.loginForm.get("email")?.value;
      const password = this.loginForm.get("password")?.value;

      this.authService.login(email, password).subscribe(
        (res)=>{
          this.authService.saveToken(res.token)
          this.router.navigate([""])
        }
        ,
        (error)=>{

        }
      )


    }

  }

}
