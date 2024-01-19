import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ResultSearchComponent } from './result-search/result-search.component';
import { StudentAuthGuard } from '../core/guards/student-auth.guard';

const routes: Routes = [
  { path: 'searchresult', component: ResultSearchComponent },
];



@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
