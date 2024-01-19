import './index.css';
import React from 'react';
import { createRoot } from 'react-dom/client';

import { Provider } from 'react-redux';
import { ThemeProvider } from './ThemeContext'; 
import 'bootstrap/dist/css/bootstrap.min.css';
import App from './App';
import store from './reducers/store';
import { BrowserRouter as Router } from 'react-router-dom';
import { getAllPosts } from './actions/blogActions';

const root = createRoot(document.getElementById('root'));
const storedPosts = JSON.parse(localStorage.getItem('blogPosts')) || [];
store.dispatch(getAllPosts(storedPosts));

root.render(
  <Router>
    <Provider store={store}>
      <ThemeProvider>
        <App />
      </ThemeProvider>
    </Provider>
  </Router>
);
