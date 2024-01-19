import { Component, ComponentFactoryResolver, ViewContainerRef } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../core/services/auth.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor(private _cfr: ComponentFactoryResolver, private _vcr: ViewContainerRef, private _authService:AuthService, private _router:Router) { }


  ngOnInit(): void {
 
    // User is logged in, decide what to show based on user type
    if (this._authService.isTeacherLoggedIn()) {
      this._router.navigate(['results'])

    } else if (this._authService.isStudentLoggedIn()) {
      this._router.navigate(['searchresult'])
    }
}

  async loginAsTeacher() {
    this._vcr.clear(); // Clear any previously loaded components

    const { TeacherLoginComponent } = await import('../teacher/teacher-login/teacher-login.component');
    this._vcr.createComponent(this._cfr.resolveComponentFactory(TeacherLoginComponent));
  }

  async loginAsStudent() {
    this._router.navigate(['searchresult'])
  }
}