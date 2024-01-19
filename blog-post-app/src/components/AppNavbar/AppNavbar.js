import React from 'react';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import ThemeToggle from '../ThemeToggle/ThemeToggle';
import { Link } from 'react-router-dom';
import './appNavbar.css';

function AppNavbar() {

  return (
    <Navbar className='px-3' bg="primary" variant="dark" expand="lg">
      <Navbar.Brand as={Link} to="/">Alpha Blog</Navbar.Brand>

      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav" className='justify-content-end'>
        <Nav className="me-5"> 
          <Nav.Link as={Link} to="/">Home</Nav.Link>
          <Nav.Link as={Link} to="/create">Add Post</Nav.Link>
        </Nav>
        <Nav>
          <ThemeToggle />
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
}

export default AppNavbar;
