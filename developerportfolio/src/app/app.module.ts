import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ProjectsComponent } from './projects/projects.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ProjectCardComponent } from './project-card/project-card.component';
import { TruncatePipe } from './truncate.pipe';
import { SkillComponent } from './skill/skill.component';
import { ServicesComponent } from './services/services.component';
import { NgCircleProgressModule } from 'ng-circle-progress';
import { FooterComponent } from './footer/footer.component';
import { ProjectworkComponent } from './projectwork/projectwork.component';
import { BlogListComponent } from './blog-list/blog-list.component';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProjectsComponent,
    AboutComponent,
    ContactComponent,
    NavbarComponent,
    ProjectCardComponent,
    TruncatePipe,
    SkillComponent,
    ServicesComponent,
    FooterComponent,
    ProjectworkComponent,
    BlogListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgCircleProgressModule.forRoot({
      titleColor:'#fff'
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
