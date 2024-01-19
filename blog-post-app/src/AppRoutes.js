import React from 'react';
import { Route, Routes } from 'react-router-dom';
import BlogList from './components/BlogList/BlogList';
import BlogDetail from './components/BlogDetails/BlogDetail';
import BlogForm from './components/BlogForm/BlogForm';

const AppRoutes = () => {
  return (
    <Routes>
      <Route exact path="/" element={ <BlogList/>} />
      <Route path="/blog/:id" element={<BlogDetail/>} />
      <Route path="/create" element={<BlogForm/>} />
      <Route path="/edit/:id" element={<BlogForm/>} />
    </Routes>
  );
};

export default AppRoutes;
