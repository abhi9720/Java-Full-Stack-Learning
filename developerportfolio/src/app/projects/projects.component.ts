import { Component } from '@angular/core';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent {
  projects = [
    {
      name: 'Postgram',
      liveLink: 'https://postgram-social.netlify.com/',

      githubLink: 'https://github.com/abhi9720/postgram-frontend',
      imageLink: './assets/project/homepostgram.png',
      details: "Postgram is a social media platform enabling profile searches, friend chats, post sharing, and likes. It suggests new connections and implements algorithms to display users' own posts and those of their followers. The project also focuses on designing a user profile section for post management and connection control. Postgram aims to offer a user-friendly interface, personalized content, and efficient connection management for seamless social interactions.",
      technologies: ['React','Node.JS','MongoDB', 'Socket.Io','Material-UI']
    },
    {
      name: 'DSA Task Manager',
      liveLink: 'https://dsataskmanager.herokuapp.com/',
      githubLink: 'https://github.com/abhi9720/ProgrammersTODO',
      imageLink: './assets/project/homedtm.png',
      details: 'Developed a web application for efficient studying and question tracking. Key features include the ability to add, update, and delete questions, set revision deadlines, and receive separate reminders for questions due on a specific day. Authentication is streamlined using Google OAuth Authentication. The application aims to enhance studying productivity and organization.',
      technologies: [
        'Nodejs',
        'Mongodb',
        'Cloudinary',
        'Express',
        'Passport-Google-Auth'
        ]
    },
    
    {
      name: 'DevConnector',
      liveLink: 'https://helptofixit.netlify.app/',
      githubLink: 'https://github.com/abhi9720/devconnector-backend',
      imageLink: './assets/project/helptofixit.png',
      details: 'Dev Connector is a platform designed for developers to ask questions, engage in discussions, and seek advice from the community. It also allows developers to create and maintain their profiles, showcasing their experience, education, and GitHub projects. The platform aims to foster a collaborative environment where developers can connect, learn, and showcase their skills and achievements.',
      technologies: ['Nodejs', 'Redux', 'Material-UI','Reactjs','Mongodb']
    },
    {
      name: 'Scrap WorldCup 2019',
      liveLink: '',
      githubLink: 'https://github.com/abhi9720/Scrap_WorldCup_2019',
      imageLink: './assets/project/crickbuzz.jpeg',
      details: '',
      technologies: ['JavaScript','Puppeteer','HTML','minimist']
    },
    {
      name: 'Yelp Camp',
      liveLink: '',
      githubLink: 'https://github.com/example/project3',
      imageLink: './assets/project/yelpcamp.png',
      details: 'Yelp Camp is a Node.js project developed as part of the Udemy Web Development course by Colt Steele. It follows the Model-View-Controller (MVC) architecture and allows users to perform full CRUD operations. Users can register and login, add campgrounds and reviews, delete camps, rate camps, and upload images. The project is built with HTML5, CSS3, Bootstrap 5, Node.js, Express.js, MongoDB, and other technologies. It features authentication, authorization, account management, and basic functionalities like camp location on a map and search capabilities.',
      technologies: ["Node.js",  "MongoDB",  "Passport",  "Cloudinary", "Mapbox"]
    },
    {
      name: 'Qubit',
      liveLink: 'https://nitmn-qubit.netlify.app/',
      githubLink: 'https://github.com/abhi9720/Qubit',
      imageLink: './assets/project/qubit.png',
      details: 'Qubit is a coding club website developed for NIT Manipur. This project is built using React.js, Tailwind CSS, and Cloudinary. It utilizes dependencies such as react-carousel-minimal, react-dom, react-modal, react-router-dom, and react-scripts. Qubit serves as a platform for the coding club, providing information, resources, and updates. It offers features like a minimal carousel, modals, and routing. The website aims to enhance the coding community at NIT Manipur through a user-friendly interface and efficient utilization of modern web technologies.',
      technologies:["React.js", "Tailwind CSS", "Cloudinary"]
    }
    // Add more projects as needed
  ];
}
