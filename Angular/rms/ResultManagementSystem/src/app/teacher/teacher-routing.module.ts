import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResultAddComponent } from './result-add/result-add.component';
import { ResultEditComponent } from './result-edit/result-edit.component';
import { ResultListComponent } from './result-list/result-list.component';
import { TeacherAuthGuard } from '../core/guards/teacher-auth.guard';
import { NotFoundComponent } from '../not-found/not-found.component';

const routes: Routes = [
  { path: 'result/add', component: ResultAddComponent, canActivate: [TeacherAuthGuard] },
  { path: 'result/edit/:rollNo', component: ResultEditComponent, canActivate: [TeacherAuthGuard] },
  { path: 'results', component: ResultListComponent, canActivate: [TeacherAuthGuard] }
  ,
{ path: '**', component: NotFoundComponent,  pathMatch:"full"
}
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherRoutingModule { }
