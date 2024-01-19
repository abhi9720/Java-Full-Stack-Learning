import { Component } from '@angular/core';

@Component({
  selector: 'app-skill',
  templateUrl: './skill.component.html',
  styleUrls: ['./skill.component.css']
})
export class SkillComponent {

  skillCategories: string[] = ['Frontend', 'Backend', 'Databases', 'Technology', 'Cloud Services'];

  skills: Skill[] = [
    { name: 'Java', category: 'Backend', level: 80 },
    { name: 'Spring Boot', category: 'Backend', level: 75 },
    { name: 'Node.js', category: 'Backend', level: 70 },
    { name: 'HTML', category: 'Frontend', level: 85 },
    { name: 'CSS', category: 'Frontend', level: 80 },
    { name: 'JavaScript', category: 'Frontend', level: 75 },
    { name: 'Angular', category: 'Frontend', level: 85 },
    { name: 'React.js', category: 'Frontend', level: 70 },
    { name: 'MongoDB', category: 'Databases', level: 75 },
    { name: 'RestAPI', category: 'Databases', level: 80 },
    { name: 'Git (GitHub, GitLab)', category: 'Technology', level: 85 },
    { name: 'VS Code', category: 'Technology', level: 90 },
    { name: 'Eclipse', category: 'Technology', level: 75 },
    { name: 'Multithreading', category: 'Technology', level: 70 },
    { name: 'JDBC', category: 'Technology', level: 80 },
    { name: 'Maven', category: 'Technology', level: 75 },
    { name: 'MySQL', category: 'Databases', level: 50 },
    { name: 'AWS S3', category: 'Cloud Services', level: 80 },
    { name: 'AWS EC2', category: 'Cloud Services', level: 75 },
    { name: 'AWS Elastic Beanstalk', category: 'Cloud Services', level: 70 },
    { name: 'AWS Lambda', category: 'Cloud Services', level: 75 },
    { name: 'Azure App Service', category: 'Cloud Services', level: 80 },
    { name: 'Azure Blob Storage', category: 'Cloud Services', level: 75 },
    { name: 'Azure Virtual Machines', category: 'Cloud Services', level: 70 },
    { name: 'Azure SQL Database', category: 'Cloud Services', level: 75 },
  ];

  constructor() { }

  getSkillsByCategory(category: string): Skill[] {
    return this.skills.filter(skill => skill.category === category);
  }
}

interface Skill {
  name: string;
  category: string;
  level: number;
}
