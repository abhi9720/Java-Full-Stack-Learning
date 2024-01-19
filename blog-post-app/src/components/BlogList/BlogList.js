import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { getAllPosts, deletePost } from '../../actions/blogActions';
import { Container, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom'
import './blogList.css';


const truncateText = (text, maxWords) => {
  const words = text.split(' ');
  if (words.length <= maxWords) {
    return text;
  }
  return words.slice(0, maxWords).join(' ') + '...';
};

const BlogList = () => {
  const dispatch = useDispatch();
  const posts = useSelector((state) => state.blog.posts);
  const navigate = useNavigate();

  useEffect(() => {
    console.log(dispatch(getAllPosts()));;
  }, [dispatch]);

  const handleDelete = (postId) => {
    dispatch(deletePost(postId));
  };

  return (
    <Container className="mt-3">
      <h2 className='mb-3'>All Blog Posts</h2>
      <div className="card-grid">
        {posts.map((post) => (
          <div key={post.id} className='card mb-4 theme-card shadow-lg'>
            <div className='card-body'>
              <h5 className='card-title'>{truncateText(post.title, 10)}</h5>
              <p className={`card-text mb-3`}>{truncateText(post.content, 60)}</p>
              <Button
                variant="primary me-2"
                onClick={() => {
                  navigate(`/blog/${post.id}`);
                }}
              >
                View
              </Button>
              <Button
                variant="danger"
                onClick={() => handleDelete(post.id)}
              >
                Delete
              </Button>
            </div>
          </div>
        ))}
      </div>


      {
        posts.length === 0 &&

        <div className="mt-5">
          <div className='alert alert-primary' >
          <h4 class="alert-heading">No Post To Display here</h4>
            <p>
              Start With Creating new Post ? {' '}
              <Link  to="/create">Create New Post Here</Link>.
            </p>
          </div>
        </div>
      }

    </Container>
  );
};

export default BlogList;
