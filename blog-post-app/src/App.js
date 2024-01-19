import React from 'react';
import './App.css';
import AppRoutes from './AppRoutes';
import AppNavbar from './components/AppNavbar/AppNavbar'; 

function App() {
  return (
    <div className="App">
        <AppNavbar/>
        <AppRoutes /> 
    </div>
  );
}

export default App;
