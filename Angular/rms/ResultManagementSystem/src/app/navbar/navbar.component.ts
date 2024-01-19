import { Component, OnInit } from '@angular/core';
import { AuthService } from '../core/services/auth.service';
import { Router } from '@angular/router';
import { AlertService } from '../core/services/alert.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {


  constructor(private _authservice: AuthService, private _router: Router, private _alert:AlertService) { }
  ngOnInit(): void {



  }


 

  isLoggedIn() {
    return this._authservice.isTeacherLoggedIn();
  }


  logoutUser() {
    this._alert.showSuccess("Logout Sucessfully")
    this._authservice.logOutUser();
    this._router.navigate(['']);
  }

}
