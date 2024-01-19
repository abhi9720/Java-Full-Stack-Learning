import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TeacherRoutingModule } from './teacher-routing.module';
import { ResultAddComponent } from './result-add/result-add.component';
import { ResultEditComponent } from './result-edit/result-edit.component';
import { ResultListComponent } from './result-list/result-list.component';
import { TeacherLoginComponent } from './teacher-login/teacher-login.component';
import {ReactiveFormsModule} from '@angular/forms'
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    ResultAddComponent,
    ResultEditComponent,
    ResultListComponent,
    TeacherLoginComponent
    
  ],
  imports: [
    CommonModule,
    TeacherRoutingModule,
    ReactiveFormsModule,
    NgbModule
  ],
  exports:[
    TeacherLoginComponent
  ]
})
export class TeacherModule { }
