import React, { useEffect } from 'react';
import { useTheme } from '../../ThemeContext';
import Button from 'react-bootstrap/Button';
import './themeToggle.css'; 
const ThemeToggle = () => {
  const { theme, toggleTheme } = useTheme();

  const toggleThemeHandler = () => {
    toggleTheme();
    document.documentElement.classList.toggle('light-theme', theme === 'light');
    document.documentElement.classList.toggle('dark-theme', theme === 'dark');
  };

  useEffect(() => {
    document.documentElement.classList.toggle('light-theme', theme === 'light');
    document.documentElement.classList.toggle('dark-theme', theme === 'dark');
  }, [theme]);

  return (
    <Button
      variant="outline-primary"
      className={`theme-toggle-button ${theme === 'light' ? 'light' : 'dark'}`}
      onClick={toggleThemeHandler}
    >
       
        {theme === 'light' ? '☀️' : '🌙'}
       
    </Button>
  );
};

export default ThemeToggle;
