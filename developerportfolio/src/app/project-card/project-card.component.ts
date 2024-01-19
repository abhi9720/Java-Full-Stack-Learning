import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-project-card',
  templateUrl: './project-card.component.html',
  styleUrls: ['./project-card.component.css']
})
export class ProjectCardComponent {
  @Input() projectName!: string;
  @Input() liveLink!: string;
  @Input() detailLink!: string;
  @Input() githubLink!: string;
  @Input() imageLink!: string;
  @Input() projectDetails!: string;
  @Input() technologies!: string[];

  isModalOpen = false;

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }
}
