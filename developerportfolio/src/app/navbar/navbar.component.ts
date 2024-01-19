import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  isNavbarVisible = false;
  bgwhite = false;
  isDarkMode = false; // Add this variable

  isMobileView(): boolean {
    return window.innerWidth < 768;
  }

  @HostListener('window:scroll')
  onWindowScroll() {
    const isMobile = this.isMobileView();
    this.bgwhite = window.scrollY > 0;

    if (!isMobile) {
      this.isNavbarVisible = window.scrollY > 0;
      this.isDarkMode = this.isNavbarVisible; // Update isDarkMode based on scroll position
    }
  }

  toggleNavbarVisibility(): void {
    this.isNavbarVisible = !this.isNavbarVisible;
    this.isDarkMode = this.isNavbarVisible; // Set dark mode based on navbar visibility
  }

  closeNavbar(): void {
    this.isNavbarVisible = false;
  }
}
