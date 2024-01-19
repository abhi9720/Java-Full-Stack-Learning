import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectworkComponent } from './projectwork.component';

describe('ProjectworkComponent', () => {
  let component: ProjectworkComponent;
  let fixture: ComponentFixture<ProjectworkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectworkComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectworkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
