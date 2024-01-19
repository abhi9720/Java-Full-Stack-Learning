import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { addPost, editPost } from '../../actions/blogActions';
import { Container, Button } from 'react-bootstrap'; 
import { useNavigate, useParams } from 'react-router-dom';
import './blogForm.css';

const BlogForm = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const post = useSelector((state) =>
    state.blog.posts.find((p) => p.id === parseInt(id, 10))
  );

  useEffect(() => {
    if (post) {
      setFormData({
        title: post.title,
        content: post.content,
      });
    }
    else{

      setFormData({
        title: '',
        content: '',
      });

    }
  }, [post]);

  const dispatch = useDispatch();
  const [formData, setFormData] = useState({
    title: post ? post.title : '',
    content: post ? post.content : '',
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const id = Date.now();

    if (post) {
      dispatch(editPost({ ...post, ...formData }));
      
    } else {
      dispatch(addPost({ ...formData, id }));
      
    }
    setFormData({ title: '', content: '' });
    navigate(-1)
  };

  if(id && !post){
    return (
      <div>No Post Found</div>
    )
  }
  return (
    <Container className="form-container mt-3 shadow">
      <h2 className="mb-4">{id ? 'Edit Post' : 'Add New Post'}</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="title" className="form-label">
            Title:
          </label>
          <input
            type="text"
            id="title"
            name="title"
            value={formData.title}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="content" className="form-label">
            Content:
          </label>
          <textarea
            id="content"
            rows="8"
            name="content"
            value={formData.content}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>
        <Button variant="primary" type="submit">
          {post ? 'Save Changes' : 'Add Post'}
        </Button>
      </form>
    </Container>
  );
};

export default BlogForm;
